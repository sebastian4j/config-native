package filtros;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseFilter extends ZuulFilter {
    @Autowired
    private FilterUtils fu;
    private static final Logger LOGGER = Logger.getLogger(TrackingFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext rc = RequestContext.getCurrentContext();
        LOGGER.info("agregando id en response " + fu.getCorrelationId());
        rc.getResponse().addHeader(FilterUtils.CORRELATION_ID, 
                fu.getCorrelationId());
        LOGGER.info("request hacia " + rc.getRequest().getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
