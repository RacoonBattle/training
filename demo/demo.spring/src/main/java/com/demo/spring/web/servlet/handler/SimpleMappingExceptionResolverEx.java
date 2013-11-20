package com.demo.spring.web.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class SimpleMappingExceptionResolverEx extends SimpleMappingExceptionResolver {

    private ObjectMapper mapper = new ObjectMapper();

    public SimpleMappingExceptionResolverEx() {
        mapper.configure(Feature.AUTO_CLOSE_TARGET, false);
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
            Object _handler, Exception ex) {

        HandlerMethod handler = (HandlerMethod) _handler;

        if (handler.getMethod().isAnnotationPresent(ResponseBody.class)) {
            errorJson(response, ex);
            return new ModelAndView(); // skip other resolver and view render
        }

        return super.doResolveException(request, response, handler, ex);
    }

    // case: XXXException => xxxcode, e.msg
    // case: CodedException => e.code, e.msg
    private void errorJson(HttpServletResponse response, Exception ex) {

        response.setContentType("application/json;charset=UTF-8");

        // ex => SQLException => "数据库异常"
        // ex => IOException => ""

        Error json = new Error();
        json.errcode = 1;
        json.errmsg = ex.getMessage();

        try {
            mapper.writeValue(response.getOutputStream(), json);
        } catch (Exception e) {
            logger.error("flush exception json", e);
        }
    }

    class Error {
        private static final boolean ret = false;
        private Object errcode;
        private Object errmsg;

        public boolean isRet() {
            return ret;
        }

        public Object getErrcode() {
            return errcode;
        }

        public Object getErrmsg() {
            return errmsg;
        }
    }
}