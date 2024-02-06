package com.jsp.ecommerce.responsedto;

import com.jsp.ecommerce.enums.UserRole;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
	private int userId;
	private String userName;
	private String email;
	private boolean isEmailVerified;
	private UserRole userRole;
	private boolean isDeleted;
}
