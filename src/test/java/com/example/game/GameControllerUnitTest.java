package com.example.game;

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

@WebMvcTest(GameController.class)
public class GameControllerUnitTest {

	@MockBean
	private GameRaitingService gameRaitingService;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void givenName_returnsRaiting() throws Exception {
		org.mockito.Mockito.when(gameRaitingService.getRating("Leauge of Legends")).thenReturn(5);
		
		mockMvc.perform(get("/games/rating").param("gameName", "Leauge of Legends"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("5")));
	}

}
