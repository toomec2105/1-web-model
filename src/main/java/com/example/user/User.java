package com.example.user;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data 
@lombok.NoArgsConstructor 

public class User {

	private Long id;
	private String email;
	private String password;
	private String role;
	private String username;
	
	public User(String email, String password, String role, String username) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.username = username;
	}

	public User(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
}