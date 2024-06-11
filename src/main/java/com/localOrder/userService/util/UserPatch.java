package com.localOrder.userService.util;

import org.springframework.stereotype.Component;

import com.localOrder.userService.dto.UserDTO;
import com.localOrder.userService.model.User;

import jakarta.transaction.Transactional;

import java.lang.reflect.Field;

@Component
public class UserPatch {
	
	@Transactional
	public void buildPatchObject(UserDTO updateDTO, User existingUser) throws IllegalArgumentException, IllegalAccessException {
		
		if(updateDTO.getPhone() != null && updateDTO.getPhone()!=existingUser.getPhone()) {
			existingUser.setPhone(updateDTO.getPhone());
		}
		if(updateDTO.getUserEmail() != null && updateDTO.getUserEmail()!=existingUser.getUserEmail()) {
			existingUser.setUserEmail(updateDTO.getUserEmail());
		}
		if(updateDTO.getUserName()!=null && updateDTO.getUserName()!= existingUser.getUsername()) {
			existingUser.setName(updateDTO.getUserName());
		}
	}

}
