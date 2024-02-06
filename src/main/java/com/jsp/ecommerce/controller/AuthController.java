package com.jsp.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.service.AuthServiceI;

public class AuthController {
	@Autowired
	private AuthServiceI userServiceI;

	@PostMapping("/users")
	public void addUser(@RequestBody UserRequestDTO userRequestDTO) {
		userServiceI.addUser(userRequestDTO);
	}

}
