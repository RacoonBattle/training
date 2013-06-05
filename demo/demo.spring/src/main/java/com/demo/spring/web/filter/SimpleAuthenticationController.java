package com.demo.spring.web.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.demo.spring.web.util.RequestUtil;

public class SimpleAuthenticationController implements AuthenticationController {

    // 可直接访问页面列表
    private List<String> noCheckRequiredPaths = new ArrayList<String>();

    @Override
    public boolean isAuthenticated(String pathInfo, ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;

        for (Cookie cookie : request.getCookies()) {
            if ("username".equals(cookie.getName())) {
                return StringUtils.hasText(cookie.getValue());
            }
        }

        return false;
    }

    @Override
    public boolean isCheckRequired(ServletRequest req) {
        return !noCheckRequiredPaths.contains(RequestUtil.getPathInfo(req));
    }

    public void setNoCheckRequiredPaths(List<String> noCheckRequiredPaths) {
        this.noCheckRequiredPaths = noCheckRequiredPaths;
    }
}
