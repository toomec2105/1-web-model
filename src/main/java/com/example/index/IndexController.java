package com.example.index;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.User;
import com.example.user.UserController;

@RestController
@RequestMapping
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private List<User> usersDb = new ArrayList<>();
	
	@GetMapping
	public String showIndex() {
		logger.info("----------------------> Entering /showIndex"); 
		 return "My index page is here";
	}
		
	@GetMapping("/help")
	public String help() {
		logger.info("----------------------> Entering /help"); 
		 return "This is help page";
	}
	
	@GetMapping("/about")
	public String about() {
		logger.info("----------------------> Entering /about"); 
		 return "This page is an example of a web app";
	}
	
}
