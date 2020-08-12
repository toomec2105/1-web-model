package com.example.game;

import org.springframework.stereotype.Component;

@Component
public class GameRaitingService {
	public Integer getRating(String name) {
		Integer output = null;
		switch (name) {
		case "Leauge of Legends":
			output = 5;
			break;

		case "Starcraft2":
			output = 4;
			break;
		default:
			output = -1;
			break;
		}
		return output;
	}
}
