package com.example.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // = @Controller @ResponseBody
@RequestMapping("users") // modifies the path for each method in this class
//@RequestMapping maps all http methods
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private List<User> usersDb = new ArrayList<>();

	@GetMapping(path = "/all")
	public List<User> showAllUsers() {
		logger.info("----------------------> Entering /all");
		// if list.size()=0 frontend will deal with it
		return usersDb;
	}

	// problem : user data is not sent
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {

		// problem1: what if user has non-existant properties?
		logger.info("----------------------> Entering /add");
		Long id = (long) usersDb.size() + 1;
		User newUser = new User(id, user.getUsername());

		boolean succes = usersDb.add(newUser);
		return succes ? newUser : null;
	}

	@DeleteMapping("/delete/{id}")
	public User deleteUser(@PathVariable String id) {
		User deleted = null;
		try {
			deleted = findByIdInternal(Long.parseLong(id));
		} catch (NumberFormatException e) {
			// problem: app crashes
			// solutions: redirect to some error page, throw a custom exeption
			e.printStackTrace();
		}
		boolean succes = usersDb.remove(deleted);
		return succes ? deleted : null;
	}

	// problem : user data is not sent
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		User updatedUser = new User(user.getId(), user.getUsername());
		User toUpdate = findByIdInternal(user.getId());

		return usersDb.set(usersDb.indexOf(toUpdate), updatedUser);
	}

	@GetMapping("/find/{id}")
	public User findUser(@PathVariable String id) {

		return findByIdInternal(Long.parseLong(id));
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

	private User findByIdInternal(Long id) {
		User found = null;
		for (User user : usersDb) {
			if (user.getId() == id) {
				found = user;
			}
		}

		if (found == null) {
			throw new UserNotFoundException();
		}
		return found;
	}
}
