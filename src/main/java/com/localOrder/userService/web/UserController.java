package com.localOrder.userService.web;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.localOrder.userService.config.JwtService;
import com.localOrder.userService.dto.CreateUserDTO;
import com.localOrder.userService.dto.UserAuthDTO;
import com.localOrder.userService.dto.UserDTO;
import com.localOrder.userService.dto.UserJwtTokenDTO;
import com.localOrder.userService.service.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotAuthorizedException;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<UserJwtTokenDTO> loginUser(@RequestBody UserAuthDTO userDTO) {
		System.out.println(userDTO.getEmail());
		UserJwtTokenDTO tokenDTO = userService.generateToken(userDTO);
		return new ResponseEntity<>(tokenDTO,HttpStatus.OK);
	}
	
	@PostMapping("/registration")
	public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO userDTO) {
		UserDTO createdUser = userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{userName}")
	public ResponseEntity<UserDTO> getUserByUserId(@PathVariable("userName") int userId){
		UserDTO userDTO = userService.getUser(userId);
		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping(path="/uploadPic/{userId}") public ResponseEntity<String>
	 * uploadProfile(HttpServletRequest request,@PathVariable("userName")int
	 * userId,@RequestParam("profileImage") MultipartFile file) throws
	 * FileNotFoundException, IOException{ String imageURL =
	 * storageService.uploadProfileImage(userId,file); return new
	 * ResponseEntity<>(imageURL,HttpStatus.OK); }
	 */
	/*
	 * @GetMapping("/validate/{token}") public ResponseEntity<Boolean>
	 * validateToken(@PathVariable("token") String token){ try {
	 * jwtService.validateToken(token); return new
	 * ResponseEntity<>(true,HttpStatus.OK); }catch(Exception e) { throw new
	 * NotAuthorizedException("Invalid request"); } }
	 */

}
