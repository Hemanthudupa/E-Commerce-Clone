package com.jsp.ecommerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.ecommerce.requestdto.AuthRequestDTO;
import com.jsp.ecommerce.requestdto.OTPModel;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.responsedto.AuthResponseDTO;
import com.jsp.ecommerce.responsedto.SimpleResponseStructure;
import com.jsp.ecommerce.responsedto.UserResponseDTO;
import com.jsp.ecommerce.util.ResponseStructure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthServiceI {

	ResponseEntity<ResponseStructure<UserResponseDTO>> register(UserRequestDTO userRequestDTO);

	ResponseEntity<ResponseStructure<UserResponseDTO>> verifyOTP(OTPModel otp);

	ResponseEntity<ResponseStructure<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO,
			HttpServletResponse httpServletResponse);

	public ResponseEntity<SimpleResponseStructure> logoutTraditional(HttpServletRequest request,
			HttpServletResponse response);

	ResponseEntity<SimpleResponseStructure> logout(String accessToken, String refreshToken,
			HttpServletResponse response);

}
