package com.example.index;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.index.IndexController;

@WebMvcTest(IndexController.class)
public class IndexControllerUnitTest {

	@MockBean
	private HelpService userHelpService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void aboutShouldReturnMessage() throws Exception {
		mockMvc.perform(get("/about")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("This page is an example of a web app")));
	}

	@Test
	public void givenTopicAndEmail_helpShouldReturnMessage() throws Exception {
		org.mockito.Mockito.when(userHelpService.getHelp("login")).thenReturn("Here is our help on login.");
		org.mockito.Mockito.when(userHelpService.sendEmail("jeszcze@interia.pl"))
				.thenReturn("Email has been sent to: jeszcze@interia.pl");

		mockMvc.perform(get("/help").param("topic", "login").param("email", "jeszcze@interia.pl"))
		        .andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Here is our help on login.")))
				.andExpect(content().string(containsString("Email has been sent to: jeszcze@interia.pl")));
				// .andExpect(content()). is instance of HelpResponse
	}

}
