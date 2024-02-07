package com.jsp.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicateEmailException extends RuntimeException {
	private String message;
}
