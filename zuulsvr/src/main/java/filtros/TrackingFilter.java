package filtros;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Enumeration;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrackingFilter extends ZuulFilter {

    @Autowired
    private FilterUtils fu;
    private static final Logger LOGGER = Logger.getLogger(TrackingFilter.class);
    @Value("${signing.key}")
    private String key;

    @Override
    public Object run() {
        RequestContext rc = RequestContext.getCurrentContext();
        final HttpServletRequest request = rc.getRequest();
        LOGGER.info(request.getRequestURI());
        LOGGER.info(request.getServerPort());
        LOGGER.info(request.getServerName());
        LOGGER.info(request.getLocalAddr());
        for (Entry<String, Object> e : rc.entrySet()) {
            LOGGER.info(e.getKey() + " --> " + e.getValue());
        }
        final Enumeration<String> hdrs = request.getHeaderNames();
        while (hdrs.hasMoreElements()) {
            final String hd = hdrs.nextElement();
            LOGGER.info(hd + " >> " + request.getHeader(hd));
        }
        if (fu.isCorrelationIdPresent()) {
            LOGGER.info("tmx-correlation-id found in tracking filter: " + fu.getCorrelationId());
        } else {
            fu.setCorrelationId(fu.generateCorrelationId());
        }
        LOGGER.debug("tmx-correlation-id generated in tracking filter: " + fu.getCorrelationId());
        RequestContext ctx = RequestContext.getCurrentContext();
        LOGGER.debug("Processing incoming request for " + ctx.getRequest().getRequestURI());
        mostrarSaludo();
        return null;
    }

    public void mostrarSaludo() {
        if (fu.getAuthToken() != null) {
            LOGGER.info(key);
            String authToken = fu.getAuthToken().replace("Bearer ", "");
            try {
                Claims cls = Jwts.parser().setSigningKey(key.getBytes("UTF-8")).parseClaimsJws(authToken).getBody();
                LOGGER.info("desde jwt: " + cls.get("saludo"));
                LOGGER.info("desde jwt: " + cls.get("user_name"));
            } catch (Exception e) {
                LOGGER.error("error jwt", e);
            }
        } else {
            LOGGER.info("sin " + FilterUtils.AUTHORIZATION);
        }
//try {
//Claims claims =
//Use JWTS class to parse out the
//Jwts.parser()
//token, passing in the signing key
//➥ .setSigningKey(
//➥
//used to sign the token.
//➥ serviceConfig
//.getJwtSigningKey()
//.getBytes("UTF-8"))
//.parseClaimsJws(authToken)
//.getBody();
//result = (String) claims.get("organizationId");
//}
//catch (Exception e){
//e.printStackTrace();
//}
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

}
