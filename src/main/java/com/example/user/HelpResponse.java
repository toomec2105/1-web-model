package com.example.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class HelpResponse {
	private String pageInfo;
	private String helpContents;
	private String emailSentConfirmation;
}
