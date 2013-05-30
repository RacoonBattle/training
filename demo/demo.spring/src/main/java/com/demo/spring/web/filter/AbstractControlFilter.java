package com.demo.spring.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 用于进行访问控制的Controller Bean的抽象类。
 * <p/>
 * 该类委托从DI容器获取的实际Controller Bean进行实际控制。
 * <h5>使用方法</h5>
 * 使用该类的具体实现类时，需要在部署描述文件（web.xml）及Bean定义文件中进行如下配置。 这里的Bean定义id为sampleXxxController，&lt;bean&gt;的class属性为Filter接口的具体实现类。
 * 部署描述文件（web.xml）
 * <pre>
 * &lt;filter&gt;
 *   &lt;filter-name&gt;
 *     xxxControlFilter
 *   &lt;/filter-name&gt;
 *   &lt;filter-class&gt;
 *     com.qunar.xxx.xxx.web.filter.XxxControlFilter
 *   &lt;/filter-class&gt;
 *   &lt;init-param&gt;
 *     &lt;param-name&gt;controller&lt;/param-name&gt;
 *     &lt;param-value&gt;
 *       "sampleXxxController"
 *     &lt;/param-value&gt;
 *   &lt;/init-param&gt;
 * &lt;/filter&gt;
 * 
 * &lt;filter-mapping&gt;
 *   &lt;filter-name&gt;xxxControlFilter&lt;/filter-name&gt;
 *   &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
 * &lt;/filter-mapping&gt;
 * 
 * &lt;error-page&gt;
 *   &lt;exception-type&gt;
 *     com.qunar.xxx.xxx.exception.XxxException
 *   &lt;/exception-type&gt;
 *   &lt;location&gt;/XxxError.jsp&lt;/location&gt;
 * &lt;/error-page&gt; </pre>
 * 
 * Bean定义文件
 * <pre>&lt;bean id=&quot;sampleXxxController&quot;
 *       class=&quot;com.qunar...SampleXxxController&quot; /&gt;</pre>
 * 当Bean定义文件中元素&lt;bean&gt;的id属性使用默认值时，部署描述文件（web.xml）中元素&lt;filter&gt; 的&lt;init-param&gt;属性可省略。
 * 
 * @param <E> 指定Controller类型
 */
public abstract class AbstractControlFilter<E> implements Filter {

    /** 日志 */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Filter配置
     */
    protected FilterConfig config = null;

    /**
     * 在服务启动时，由容器调用执行。
     * Filter实例化之后，由容器对init方法进行一次调用。
     * 
     * @param config {@link FilterConfig}
     * @throws javax.servlet.ServletException 初始化异常
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig config) throws ServletException {
        this.setConfig(config);
    }

    /**
     * 设置Filter配置信息。
     * 
     * @param config Filter配置
     */
    protected void setConfig(FilterConfig config) {
        if (log.isDebugEnabled()) {
            log.debug("setConfig() called.");
        }
        this.config = config;
    }

    /**
     * 从DI容器获取Controller
     * 
     * @return E 获取到的Controller
     */
    protected E getController() {

        if (log.isDebugEnabled()) {
            log.debug("setController() called.");
        }

        // 获取WebApplicationContext
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());

        if (wac == null) {
            log.error("Failed to get the WebApplicationContext from servletContext.");
            return null;
        }
        // 将Bean定义文件中Controller的id做为Filter的初始化参数
        String controllerId = config.getInitParameter("controller");
        if (controllerId == null || "".equals(controllerId)) {
            if (log.isDebugEnabled()) {
                log.debug("init parameter 'controller' isn't defined or " + "empty");
            }
            controllerId = getDefaultControllerBeanId();
        }

        if (log.isDebugEnabled()) {
            log.debug("controller bean id = \"" + controllerId + "\"");
        }

        // 从DI容器中获取Controller Bean
        E controller = null;
        try {
            controller = (E) wac.getBean(controllerId, getControllerClass());
        } catch (NoSuchBeanDefinitionException e) {
            log.error("not found " + controllerId + ". " + "controller bean not defined in Beans definition file.", e);
            throw new RuntimeException(getErrorCode(), e);
        } catch (BeanNotOfRequiredTypeException e) {
            log.error("controller not implemented " + getControllerClass().toString() + ".", e);
            throw new RuntimeException(getErrorCode(), e);
        } catch (BeansException e) {
            log.error("bean generation failed.", e);
            throw new RuntimeException(getErrorCode(), e);
        }

        return controller;
    }

    /**
     * 获取控制器接口类型
     * 
     * @return 该Filter使用的Controller类别
     */
    protected abstract Class<E> getControllerClass();

    /**
     * 获取生成Controller失败时的错误代码。
     * 
     * @return 错误代码
     */
    protected abstract String getErrorCode();

    /**
     * 获取默认Controller id，用于从DI容器获取具体的Controller实例
     * 
     * @return 默认id
     */
    public abstract String getDefaultControllerBeanId();

    /**
     * 执行访问控制。
     * 
     * @param req HTTP请求
     * @param res HTTP响应
     * @param chain Filter链
     * @throws IOException I/O异常
     * @throws ServletException Servlet异常
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     *      javax.servlet.FilterChain)
     */
    public abstract void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException;

    /**
     * Filter执行时进行调用。
     * <p/>
     * 该类中不做任何处理。
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // 不做任何处理
    }
}