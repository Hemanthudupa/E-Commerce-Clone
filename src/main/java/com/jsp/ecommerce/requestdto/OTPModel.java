package com.jsp.ecommerce.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OTPModel {
	private String email;
	private String  otp;
}
