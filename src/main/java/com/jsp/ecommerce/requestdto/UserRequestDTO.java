package com.jsp.ecommerce.requestdto;

import com.jsp.ecommerce.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
	private String email;
	private String password;
	private UserRole userRole;
}
