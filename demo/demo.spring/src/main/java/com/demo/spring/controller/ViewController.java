package com.demo.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

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
}
