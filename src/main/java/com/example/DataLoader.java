package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.user.UserController;

@Component
public class DataLoader implements CommandLineRunner {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private UserController userController;

	
	@Override
	public void run(String... args) throws Exception {
		logger.info("----------------------> Populating db");
		userController.populateDb();
	}

}
