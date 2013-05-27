package com.demo.mybatis.practice.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.mybatis.accessor.ModelAccessor;
import com.demo.mybatis.sample.bean.User;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Resource(name = "userAccessor")
    ModelAccessor<User> userAccessor;

    // 帐户列表
    @RequestMapping(value = "/user/{key}/{value}", method = RequestMethod.GET)
    @ResponseBody
    public Map<Object, Object> user(
    		@PathVariable("key") String key,
    		@PathVariable("value") String value) {

        return null; // dataJson(accountAccessor.list(query));
    }
}
