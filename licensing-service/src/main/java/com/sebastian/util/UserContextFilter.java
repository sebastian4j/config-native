package com.sebastian.util;

import java.io.IOException;
import java.util.Enumeration;
import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author sebastian
 */
@Component
public class UserContextFilter implements Filter {

    private static final Logger logger = Logger.getLogger(UserContextFilter.class);

    @PostConstruct
    private void init() {
        logger.info("post filter ok");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("filtro invocado");
        final HttpServletRequest req = (HttpServletRequest) request;
        final Enumeration<String> hdrs = req.getHeaderNames();
        while (hdrs.hasMoreElements()) {
            final String hd = hdrs.nextElement();
            logger.info(hd + " >>>> " + req.getHeader(hd));
        }
        UserContext uc = UserContextHolder.getContext();
        uc.setCorrelationId(req.getHeader(UserContext.CORRELATION_ID));
        uc.setAuthToken(req.getHeader(UserContext.AUTH_TOKEN));
        uc.setOrgId(req.getHeader(UserContext.ORG_ID));
        uc.setUserId(req.getHeader(UserContext.USER_ID));
        uc.setAuthorization(req.getHeader(UserContext.AUTHORIZATION));
        logger.info("user context filter " + uc.getCorrelationId());
        logger.info("user context: " + uc);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
