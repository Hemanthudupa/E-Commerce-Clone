package com.jsp.ecommerce.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsp.ecommerce.entity.Customer;
import com.jsp.ecommerce.entity.Seller;
import com.jsp.ecommerce.entity.User;
import com.jsp.ecommerce.repository.AuthRepo;
import com.jsp.ecommerce.repository.CustomerRepo;
import com.jsp.ecommerce.repository.SellerRepo;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.service.AuthServiceI;

public class AuthServiceImpl implements AuthServiceI {

	@Autowired
	private AuthRepo authRepo;

	@Autowired
	private SellerRepo sellerRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@SuppressWarnings("unchecked")
	public <T extends User> T mapToUser(UserRequestDTO userRequestDTO) {
		switch (userRequestDTO.getUserRole()) {
		case SELLER: {

			Seller seller = new Seller();
			seller.setEmail(userRequestDTO.getEmail());
			seller.setPassword(userRequestDTO.getPassword());
			seller.setUserRole(userRequestDTO.getUserRole());
			return (T) seller;
		}
		case CUSTOMER: {

			Customer customer = new Customer();
			customer.setEmail(userRequestDTO.getEmail());
			customer.setPassword(userRequestDTO.getPassword());
			customer.setUserRole(userRequestDTO.getUserRole());
			return (T) customer;
		}
		default:
			return null;

		}
	}

	@Override
	public void addUser(UserRequestDTO userRequestDTO) {

		if (authRepo.existsByEmail(userRequestDTO.getEmail())) {
			User user = mapToUser(userRequestDTO);
			user.setUserName(user.getEmail().split("@")[0]);

			authRepo.save(user);
			if (user instanceof Seller) {
				Seller seller = (Seller) user;
				sellerRepo.save(seller);
			} else {
				Customer customer = (Customer) user;
				customerRepo.save(customer);
			}

		}
	}

}
