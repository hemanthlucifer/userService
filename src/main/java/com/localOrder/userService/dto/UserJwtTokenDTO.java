package com.localOrder.userService.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserJwtTokenDTO {
	
	private String jwtToken;
	
	private String userName;
	
	private int userId;

}
