package com.demo.spring.web.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getPathInfo(ServletRequest request) {
        if (request == null) {
            return null;
        }
        // 取得pathInfo
        return ((HttpServletRequest) request).getRequestURI().replaceFirst(
                ((HttpServletRequest) request).getContextPath(), "");
    }
}
