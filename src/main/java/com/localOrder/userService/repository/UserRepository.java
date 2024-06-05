package com.localOrder.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localOrder.userService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	User findByUserEmail(String email);

}
