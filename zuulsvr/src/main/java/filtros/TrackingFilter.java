package filtros;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TrackingFilter extends ZuulFilter {
    @Autowired
    private FilterUtils fu;
    private static final Logger LOGGER = Logger.getLogger(TrackingFilter.class);

    @Override
    public Object run() {
        if (fu.isCorrelationIdPresent()) {
            LOGGER.info("tmx-correlation-id found in tracking filter: " + fu.getCorrelationId());
        } else {
            fu.setCorrelationId(fu.generateCorrelationId());
        }
        LOGGER.debug("tmx-correlation-id generated in tracking filter: " + fu.getCorrelationId());
        RequestContext ctx = RequestContext.getCurrentContext();
        LOGGER.debug("Processing incoming request for " + ctx.getRequest().getRequestURI());
        return null;
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
