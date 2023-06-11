package com.academy.finalProject.model;

import lombok.Getter;

@Getter
public class JWTResponse {

	private final String role;
	private String userName;
	
	public JWTResponse(String userName,String role) {
		this.userName = userName;
		this.role = role;
	}
}
