package com.demo.spring.web.web.context.support;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求上下文信息维护接口
 * 
 * @author zhongyuan.zhang
 */
public interface RequestContextSupport {

    /**
     * 获取请求名称
     *
     * @return
     */
    String getRequestName();

    /**
     * 获取业务属性
     * 
     * @param key
     * @return
     */
    Object getProperty(String key);

    /**
     * 获取指定类型的业务属性
     * 
     * @param key
     * @param clazz
     * @return
     */
    <E> E getProperty(String key, Class<E> clazz);

    /**
     * 获取字符类型的业务属性
     * 
     * @param key
     * @return
     */
    String getPropertyString(String key);

    /**
     * 生成上下文信息
     * 
     * @param request HTTP请求
     */
    void generateContext(HttpServletRequest request);

    /**
     * 销毁上下文信息
     */
    void destroyContext();
}