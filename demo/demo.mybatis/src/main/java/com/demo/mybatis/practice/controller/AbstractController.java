package com.demo.mybatis.practice.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Map<Object, Object> dataJson(Object data) {
        Map<Object, Object> obj = new LinkedHashMap<Object, Object>();
        obj.put("ver", getVer());
        obj.put("ret", true);
        obj.put("data", data);

        return obj;
    }

    protected Map<Object, Object> errorJson(String errmsg) { // errcode ?
        Map<Object, Object> obj = new LinkedHashMap<Object, Object>();
        obj.put("ver", getVer());
        obj.put("ret", false);
        obj.put("errcode", 1);
        obj.put("errmsg", errmsg);

        return obj;
    }

    private int getVer() {
        // TODO get version
        return 1;
    }
}
