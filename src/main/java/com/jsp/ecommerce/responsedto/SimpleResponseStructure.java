package com.jsp.ecommerce.responsedto;

import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component
public class SimpleResponseStructure {
	private String message;
	private int status;
}
