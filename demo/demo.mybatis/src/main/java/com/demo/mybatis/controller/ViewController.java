package com.demo.mybatis.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.mybatis.accessor.ModelAccessor;
import com.demo.mybatis.accessor.bean.GenericQuery;
import com.demo.mybatis.sample.bean.Account;

@Controller
@RequestMapping("view")
public class ViewController extends AbstractController {

    @Resource
    private ModelAccessor<Account> accountAccessor;

    @RequestMapping("account/{key}/{value}")
    @ResponseBody
    public Object test(
            @PathVariable("key") String key,
            @PathVariable("value") String value,
            @RequestParam(value = "role", required = false) Integer role) {

        return dataJson(accountAccessor.select(new GenericQuery().fill(key, value).fill("role", role)));
    }
}
