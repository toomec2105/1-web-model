package com.example.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users") // modifies the path for each method in this class
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private List<User> usersDb = new ArrayList<>();

	/*
	 * @GetMapping public String showIndex() { logger.
	 * info("----------------------> This will map to localhost:8080 but only if @RequestMapping over class name maps to empty string"
	 * ); return "My index page is here"; }
	 */
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

	@GetMapping("/error")
	public String error() {
		logger.info("----------------------> Entering /error");
		return "This page is going to handle errors";
	}

	@GetMapping("/all")
	public List<User> showAllUsers() {
		logger.info("----------------------> Entering /all");
		return usersDb;
	}

	@GetMapping("/add")
	public User addUser() {
		User user4 = new User(4L, "White");
		boolean succes = usersDb.add(user4);
		return succes ? user4 : null;
	}

	@GetMapping("/delete/2")
	public User deleteUser() {
		User deleted = findById(2L);

		boolean succes = usersDb.remove(deleted);
		return succes ? deleted : null;
	}

	@GetMapping("/update/3")
	public User updateUser() {
		User updatedUser = new User(3L, "Black");
		User toUpdate = findById(3L);
		return usersDb.set(usersDb.indexOf(toUpdate), updatedUser);
	}

	@GetMapping("/find/1")
	public User findUser() {

		return findById(1L);
	}

	public void populateDb() {
		User user1 = new User(1L, "Green");
		User user2 = new User(2L, "Red");
		User user3 = new User(3L, "Brown");
		usersDb.add(user1);
		usersDb.add(user2);
		usersDb.add(user3);
		System.out.println(usersDb.size());
	}

	private User findById(Long id) {
		User found = null;
		for (User user : usersDb) {
			if (user.getId() == id) {
				found = user;
				return user;
			}
		}

		if (found == null) {
			// What to do?
		}
		return null;
	}
}
