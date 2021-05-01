package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.UserDetails;

@RestController
public class HelloController {
	//@RequestMapping(method = RequestMethod.GET,path = "/welcome")
	@GetMapping("/welcome")
	public String helloWorld()
	{
		return "welcome";
	}
	@GetMapping("/userDetails")
	public UserDetails getDetails()
	{
		return new UserDetails("mark", "jammer", "34");
	}
}

