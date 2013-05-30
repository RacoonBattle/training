package com.demo.spring.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.demo.spring.web.web.context.support.RequestContextSupport;

public class RequestContextHandlingFilter implements Filter {

    /**
     * RequestContextSupport对象ID
     */
    private static final String DEFAULT_CTXSUPPORT_BEANID = "ctxSupport";

    /**
     * Filter初始化，用于获取RequestContextSupport对象的参数
     */
    private static final String INITPARAM_KEY_CTXSUPPORT_BEANID = "ctxSupportBeanID";

    /**
     * RequestContextSupport
     */
    private RequestContextSupport ctxSupport = null;

    /**
     * 初始化Filter，并从应用上下文获取RequestContextSupport
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        String ctxSupportBeanID = config.getInitParameter(INITPARAM_KEY_CTXSUPPORT_BEANID);
        if (ctxSupportBeanID == null) {
            ctxSupportBeanID = DEFAULT_CTXSUPPORT_BEANID;
        }
        // 获取应用上下文
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        this.ctxSupport = (RequestContextSupport) context.getBean(ctxSupportBeanID);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try {
            ctxSupport.generateContext((HttpServletRequest) request);
            chain.doFilter(request, response);
        } finally {
            ctxSupport.destroyContext();
        }
    }

    @Override
    public void destroy() {
    }
}