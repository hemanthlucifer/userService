package com.localOrder.userService.dto;

import lombok.Data;

@Data
public class CreateUserDTO {

	private String userName;

	private String userEmail;

	private String password;
	
	private long phone;

}
