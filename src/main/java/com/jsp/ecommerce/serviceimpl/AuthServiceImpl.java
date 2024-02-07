package com.jsp.ecommerce.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ecommerce.entity.Customer;
import com.jsp.ecommerce.entity.Seller;
import com.jsp.ecommerce.entity.User;
import com.jsp.ecommerce.exceptions.UserEmailAlreadyVerifiedException;
import com.jsp.ecommerce.repository.CustomerRepo;
import com.jsp.ecommerce.repository.SellerRepo;
import com.jsp.ecommerce.repository.UserRepo;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.responsedto.UserResponseDTO;
import com.jsp.ecommerce.service.AuthServiceI;
import com.jsp.ecommerce.util.ResponseStructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthServiceI {

	private UserRepo userRepo;

	private SellerRepo sellerRepo;

	private CustomerRepo customerRepo;

	private PasswordEncoder passwordEncoder;

	private ResponseStructure<UserResponseDTO> responseStructure;

	@SuppressWarnings("unchecked")
	private <T extends User> T mapToUser(UserRequestDTO userRequestDTO) {
		User user = null;
		switch (userRequestDTO.getUserRole()) {
		case SELLER -> {
			user = new Seller();
		}
		case CUSTOMER -> {
			user = new Customer();
		}
		}

		user.setEmail(userRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		user.setUserRole(userRequestDTO.getUserRole());
		user.setUserName(user.getEmail().split("@")[0]);
		return (T) user;
	}

	public UserResponseDTO mapToResponse(User user) {
		return UserResponseDTO.builder().email(user.getEmail()).userId(user.getUserId()).isDeleted(user.isDeleted())
				.isEmailVerified(user.isEmailVerified()).userName(user.getUserName()).userRole(user.getUserRole())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> register(UserRequestDTO userRequestDTO) {

		User user = userRepo.findByUserName(userRequestDTO.getEmail().split("@")[0]).map(u -> {
			if (u.isEmailVerified())
				throw new UserEmailAlreadyVerifiedException(" email is  already verified !!!");
			else {

			}
			return u;
		}).orElseGet(() -> saveUser(mapToUser(userRequestDTO)));

		return new ResponseEntity<ResponseStructure<UserResponseDTO>>(responseStructure.setData(mapToResponse(user))
				.setMessage("Please verify through OTP sent on your mail Id ").setStatus(HttpStatus.ACCEPTED.value()),
				HttpStatus.ACCEPTED);
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T saveUser(User user) {
		User user2 = null;
		if (user instanceof Seller)
			user2 = sellerRepo.save((Seller) user);
		else if(user instanceof Customer)
			user2 = customerRepo.save((Customer) user);

		return (T) user2;
	}
}