package com.jsp.ecommerce.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ecommerce.requestdto.AuthRequestDTO;
import com.jsp.ecommerce.requestdto.OTPModel;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.responsedto.AuthResponseDTO;
import com.jsp.ecommerce.responsedto.UserResponseDTO;
import com.jsp.ecommerce.service.AuthServiceI;
import com.jsp.ecommerce.util.ResponseStructure;

import jakarta.servlet.http.HttpServletResponse;

//these controller only deals with the authenticated methods
@RestController
public class AuthController {
	@Autowired
	private AuthServiceI authServiceI;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponseDTO>> register(@RequestBody UserRequestDTO userRequestDTO) {
		return authServiceI.register(userRequestDTO);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<ResponseStructure<UserResponseDTO>> verifyOTP(@RequestBody OTPModel otpModel) {
		return authServiceI.verifyOTP(otpModel);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO,HttpServletResponse httpServletResponse) {
		return authServiceI.login(authRequestDTO,httpServletResponse);
	}

}