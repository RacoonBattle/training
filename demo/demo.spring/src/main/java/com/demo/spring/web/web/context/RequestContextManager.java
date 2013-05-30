package com.demo.spring.web.web.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求上下文管理类，用于绑定、获取和解除绑定请求信息
 */
public class RequestContextManager {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RequestContextManager.class);

    /**
     * 保持当前请求线程上下文信息
     */
    private static ThreadLocal<RequestContext> resources = new ThreadLocal<RequestContext>();

    /**
     * 获取当前请求上下文信息
     * 
     * @return 请求上下文信息
     */
    public static RequestContext getRequestContext() {
        RequestContext ctx = resources.get();

        if (ctx == null) {
            logger.error("No RequestContext bound to thread!");
            throw new IllegalStateException("No RequestContext  bound to thread [" + Thread.currentThread().getName()
                    + "]");
        }

        return ctx;
    }

    public static boolean hasRequestContext() {
        return (resources.get() != null);
    }

    /**
     * 绑定请求信息，无法重复绑定
     * 
     * @param ctx 请求上下文
     */
    public static void bindRequestContext(RequestContext ctx) {

        if (ctx == null) {
            logger.error("RequestContext cannot set null.");
            throw new IllegalArgumentException("RequestContext cannot set null.");
        }

        RequestContext alreadyBoundCtx = resources.get();

        if (alreadyBoundCtx == null) {
            resources.set(ctx);
            if (logger.isDebugEnabled()) {
                logger.debug("Bound RequestContext [" + ctx + "] to thread [" + Thread.currentThread().getName() + "]");
            }
        } else {
            logger.error("Already RequestContext bound to thread!");
            throw new IllegalStateException("Already RequestContext [" + alreadyBoundCtx + "]" + "   ["
                    + Thread.currentThread().getName() + "]");
        }
    }

    public static void unbindRequestContext() {
        RequestContext ctx = resources.get();
        if (ctx == null) {
            logger.error("No RequestContext bound to thread!");
            throw new IllegalStateException("No RequestContext  bound to thread [" + Thread.currentThread().getName()
                    + "]");
        }

        resources.remove();

        if (logger.isDebugEnabled()) {
            logger.debug("Removed RequestContext [" + ctx + "] from thread [" + Thread.currentThread().getName() + "]");
        }
    }
}