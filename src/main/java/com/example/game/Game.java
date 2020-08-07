package com.example.game;

import com.example.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data 
public class Game {
	
	private Long id;
	private String name;
	private String category;
}
