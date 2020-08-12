package com.example.user;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	public UserController userController;

	@Test
	public void aboutShouldReturnMessage() throws Exception {
		User user = userController.addUser(new User("Red"));

		mockMvc.perform(delete("/users/delete/" + user.getId())) // find a better way to add path variable 
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Red")));
	}
}
