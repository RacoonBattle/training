package com.demo.spring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.system.security.account.AccountAccessor;

@Controller
public class ViewController {

    @RequestMapping("query1")
    @ResponseBody
    public Object query1(){
        return new String[] {"alice", "frank"};
    }

    @RequestMapping("query2")
    @ResponseBody
    public Object query2() throws JsonGenerationException, JsonMappingException, IOException {
        return new ObjectMapper().writeValueAsString(new String[] { "alice", "frank" });
    }

    @RequestMapping("jsp")
    public ModelAndView jsp() {
        return new ModelAndView("hello", "name", "alice");
    }

    @RequestMapping("json")
    @ResponseBody
    public Object json() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "alice");

        return map;
    }

    @Resource(name = "mappedAccountAccessor")
    private AccountAccessor accountAccessor;

    @RequestMapping("account/{name}")
    @ResponseBody
    public Object account(@PathVariable("name") String name) {
        return accountAccessor.getAccount(name);
    }
}
