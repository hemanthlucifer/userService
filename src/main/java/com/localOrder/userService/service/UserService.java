package com.localOrder.userService.service;

import com.localOrder.userService.dto.CreateUserDTO;
import com.localOrder.userService.dto.UserAuthDTO;
import com.localOrder.userService.dto.UserDTO;
import com.localOrder.userService.dto.UserJwtTokenDTO;

public interface UserService {
	
	public UserDTO createUser(CreateUserDTO userDTO);
	public UserDTO updateUser(UserDTO userDTO);
	public UserDTO getUser(int userId);
	public UserJwtTokenDTO generateToken(UserAuthDTO userAuth);

}
