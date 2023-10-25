package com.okay.appplatform.log.LogMDC;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author XieYangYang
 * @date 2020/6/5 11:10
 */
public class MyLogFilter implements Filter {


    private String UNIQUE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String traceId = servletRequest.getHeader("traceId");
        traceId = StringUtils.isNotBlank(traceId) ? traceId : UUID.randomUUID().toString();
        MDC.put(UNIQUE_ID, traceId);

        //链路完成销毁日志跟踪id
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(UNIQUE_ID);
        }
    }

    @Override
    public void destroy() {

    }

}
