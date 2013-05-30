package com.demo.spring.web.filter;

import javax.servlet.ServletRequest;

/**
 * 认证控制
 */
public interface AuthenticationController {

    /**
     * 对于指定的请求路径，认证HTTP会话。
     *
     * @param pathInfo 路径信息
     * @param req HTTP请求
     * 
     * @return 当权限验证成功时，返回 <code>true</code>
     */
    boolean isAuthenticated(String pathInfo, ServletRequest req);

    /**
     * 是否需要进行登陆认证。
     * 
     * @param req <code>ServletRequest</code>
     * 
     * @return 请求需要进行权限验证时，返回<code>true</code>
     */
    boolean isCheckRequired(ServletRequest req);
}