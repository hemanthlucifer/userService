package com.localOrder.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(UserUpdateException.class)
	public ResponseEntity<String>userUpdateException(UserUpdateException userUpdateException){
		return new ResponseEntity<>(userUpdateException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
