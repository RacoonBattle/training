package com.demo.mybatis.sample.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.dao.QueryDAO;
import com.demo.mybatis.accessor.ModelAccessor;
import com.demo.mybatis.accessor.bean.GenericQuery;
import com.demo.mybatis.sample.bean.User;

@Controller
@RequestMapping("/view")
public class ViewController {

	@Resource
	private QueryDAO queryDAO;

	@Resource
	private ModelAccessor<User> userAccessor;

	@RequestMapping("user/{key}/{value}")
	@ResponseBody
	public Object test(
			// @RequestBody User user,
			
			@PathVariable("key") String key,
			@PathVariable("value") String value,
			@RequestParam(value = "role", required = false) Integer role) {
			// ? name=xx
			// String name = request.getParameter("name");
	
			// photo/{year}/{month}/{user}/{category}/{page}
			//
			// ? user=xx&cate=xx&page
		

		return userAccessor.select(new GenericQuery().fill(key, value).fill("role", role));
		// return queryDAO.executeForObject("user.select", new GenericQuery().fill(key, value), User.class);
	}


    @RequestMapping(value = "/responseBody", method = RequestMethod.GET)
    @ResponseBody
    public Object responseBody() {
    		Map<Object, Object> data = new HashMap<Object, Object>();
    		data.put("name", "alice");
    		data.put("address", "Beijing, China");
    		
    		
    		// <form method=get>
    		// GET http://localhost:8080/=/view/usr/~/~?name=frank
    		
    		// <form method=post>
    		// POST http://localhost:8080/=/view/usr/~/~
    		// name=frank&role=0

    		
    		
    		return data;
    		
    		//return new ModelAndView("index"); // => index.jsp
    }
    
    
	
	// POST http://localhost:8080/=/view/usr/~/~


	
    @RequestMapping(value = "/requestBody", method = RequestMethod.POST)
    @ResponseBody
    public Object requestBody(
    	    // name=frank&role=0
    		@PathVariable("name") String name,
    		@PathVariable("role") String role,

    		// {"name":"frank", "role": "USER"}
    		@RequestBody User body) {

    		System.out.println(body);
    	
    		return body;
    }
}
