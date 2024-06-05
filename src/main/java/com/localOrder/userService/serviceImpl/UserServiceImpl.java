package com.localOrder.userService.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.localOrder.userService.config.JwtService;
import com.localOrder.userService.dto.CreateUserDTO;
import com.localOrder.userService.dto.UserAuthDTO;
import com.localOrder.userService.dto.UserDTO;
import com.localOrder.userService.dto.UserJwtTokenDTO;
import com.localOrder.userService.model.User;
import com.localOrder.userService.repository.UserRepository;
import com.localOrder.userService.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StorageServiceImpl storageService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public UserDTO createUser(CreateUserDTO userDTO) {
		User user = new User();
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole("USER");
		user.setUserEmail(userDTO.getUserEmail());
		user.setName(userDTO.getUserName());
		user.setPhone(userDTO.getPhone());;
		user = userRepository.save(user);
		UserDTO createdUserDTO = new UserDTO();
		createdUserDTO.setUserEmail(user.getUserEmail());
		createdUserDTO.setPhone(user.getPhone());
		return createdUserDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUser(int userId) {
		Optional<User> user = userRepository.findById(userId);
		UserDTO userDTO = new UserDTO();
		String imageUrl = storageService.imageLink("profilePic"+userId);
		userDTO.setProfileImage(imageUrl);
		userDTO.setUserEmail(user.get().getUserEmail());
		userDTO.setUserName(user.get().getName());
		userDTO.setPhone(user.get().getPhone());
		return userDTO;
	}

	@Override
	public UserJwtTokenDTO generateToken(UserAuthDTO userAuthDTO) {
		
		
		UserJwtTokenDTO tokenDTO = new UserJwtTokenDTO();
		System.out.println("authentication started");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthDTO.getEmail(),userAuthDTO.getPassword()));
		if(authentication.isAuthenticated()) {
			System.out.println("authentication sucessfull");
			User user = userRepository.findByUserEmail(userAuthDTO.getEmail());
			if(user!=null) {
				String token = jwtService.GenerateToken(user.getUserEmail());
				tokenDTO.setJwtToken(token);
				tokenDTO.setUserName(user.getName());
				tokenDTO.setUserId(user.getUserId());
			}
		}
		
		return tokenDTO;
		
	}

	
	

}
