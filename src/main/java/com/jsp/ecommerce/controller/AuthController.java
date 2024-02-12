package com.jsp.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ecommerce.requestdto.AuthRequestDTO;
import com.jsp.ecommerce.requestdto.OTPModel;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.responsedto.AuthResponseDTO;
import com.jsp.ecommerce.responsedto.SimpleResponseStructure;
import com.jsp.ecommerce.responsedto.UserResponseDTO;
import com.jsp.ecommerce.service.AuthServiceI;
import com.jsp.ecommerce.util.ResponseStructure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//these controller only deals with the authenticated methods
@RestController
@RequestMapping("/api/v1")
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
	public ResponseEntity<ResponseStructure<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO,
			HttpServletResponse httpServletResponse) {
		return authServiceI.login(authRequestDTO, httpServletResponse);
	}

	@PostMapping("/logout-traditional")
	public ResponseEntity<SimpleResponseStructure> logoutTraditional(HttpServletRequest request,
			HttpServletResponse response) {
		return authServiceI.logoutTraditional(request, response);
	}

	@PostMapping("/logout")
	public ResponseEntity<SimpleResponseStructure> logout(
			@CookieValue(name = "accesstoken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
		return authServiceI.logout(accessToken, refreshToken, response);
	}
}