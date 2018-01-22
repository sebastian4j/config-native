package filtros;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RutasFilter extends ZuulFilter {
    private static final Logger LOGGER = Logger.getLogger(RutasFilter.class);
    

    @Override
    public boolean shouldFilter() {
        return true;
    }

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
        return null;
    }

    @Override
    public String filterType() {
        return FilterUtils.ROUTE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
