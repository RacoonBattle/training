package com.demo.mybatis.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.mybatis.accessor.ModelAccessor;
import com.demo.mybatis.accessor.bean.GenericQuery;

@Controller
@RequestMapping("view")
public class ViewController extends AbstractController {


    @Resource
    private Map<String, ModelAccessor<Object>> accessorMap;

    enum AccountQueryKey {
        name, role, accountId
    }

    // http://www...../=/{entity}/{key}/{value} ? lastLoignFrom=xxx
    // /=/account/password/123456
    // /=/account/name/alice
    @RequestMapping("{entity}/{key}/{value}")
    @ResponseBody
    public List<Object> list(
            @PathVariable("entity") String entity,
            @PathVariable("key") String key,
            @PathVariable("value") String value) {
     
        return accessorMap.get(entity).select(
                new GenericQuery(AccountQueryKey.class).fill(key, value));
    }

//    @Resource
//    private ModelAccessor<Account> accountAccessor;
//
//    @RequestMapping("account/{key}/{value}")
//    @ResponseBody
//    public Object test(
//            @PathVariable("key") String key,
//            @PathVariable("value") String value,
//            @RequestParam(value = "role", required = false) Integer role) {
//
//        return dataJson(accountAccessor.select(new GenericQuery().fill(key, value).fill("role", role)));
//    }
}
