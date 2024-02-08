package com.jsp.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSessionExpiredException extends RuntimeException {
	private String message;
}
