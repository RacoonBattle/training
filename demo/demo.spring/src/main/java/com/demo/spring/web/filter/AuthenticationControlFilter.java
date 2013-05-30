package com.demo.spring.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证控制过滤器
 */
public class AuthenticationControlFilter extends AbstractControlFilter<AuthenticationController> {

    /**
     * 判定请求是否已经通过该Filter的标示。
     */
    public static final String AUTHENTICATION_THRU_KEY = "AUTHENTICATION_THRU_KEY";

    /**
     * 默认Controller实现类
     */
    public static final String DEFAULT_AUTHENTICATION_BEAN_ID = "authenticationController";

    /**
     * 认证检测Controller生成失败时的错误代码。
     */
    private static final String AUTHENTICATION_CONTROLLER_ERROR = "errors.authentication.controller";

    /**
     * 认证控制类
     */
    private static final Class<AuthenticationController> AUTHENTICATION_CONTROLLER_CLASS = AuthenticationController.class;

    /**
     * 认证控制器
     */
    protected static AuthenticationController controller = null;

    public static AuthenticationController getAuthenticationController() {
        return controller;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        if (controller == null) {
            controller = getController();
        }
    }

    @Override
    protected Class<AuthenticationController> getControllerClass() {
        return AUTHENTICATION_CONTROLLER_CLASS;
    }

    @Override
    protected String getErrorCode() {
        return AUTHENTICATION_CONTROLLER_ERROR;
    }

    @Override
    public String getDefaultControllerBeanId() {
        return DEFAULT_AUTHENTICATION_BEAN_ID;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        if (req.getAttribute(AUTHENTICATION_THRU_KEY) == null) {
            req.setAttribute(AUTHENTICATION_THRU_KEY, "true");

            if (controller.isCheckRequired(req)) {
                if (!controller.isAuthenticated(getPathInfo(req), req)) {
                    if (log.isDebugEnabled()) {
                        log.debug("isAuthenticated() failed.");
                    }
                    //throw new UnauthenticatedException();
                    ((HttpServletResponse) res).sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }
        }
        chain.doFilter(req, res);
    }

    private static String getPathInfo(ServletRequest request) {
        if (request == null) {
            return null;
        }
        //取得pathInfo
        return ((HttpServletRequest) request).getRequestURI().replaceFirst(
            ((HttpServletRequest) request).getContextPath(), "");
    }
}