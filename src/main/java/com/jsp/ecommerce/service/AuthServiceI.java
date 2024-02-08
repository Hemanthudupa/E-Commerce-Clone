package com.jsp.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ecommerce.requestdto.OTPModel;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.responsedto.UserResponseDTO;
import com.jsp.ecommerce.util.ResponseStructure;

public interface AuthServiceI {

	ResponseEntity<ResponseStructure<UserResponseDTO>> register(UserRequestDTO userRequestDTO);

	ResponseEntity<ResponseStructure<UserResponseDTO>> verifyOTP(OTPModel otp);

}
