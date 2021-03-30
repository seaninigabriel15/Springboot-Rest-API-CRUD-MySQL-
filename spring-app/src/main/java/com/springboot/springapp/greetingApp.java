package com.springboot.springapp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class greetingApp {
	
	@RequestMapping("/")
	public @ResponseBody String greeting()
	{
		return "Hello Sean";
	}
	
	

}
