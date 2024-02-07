package com.jsp.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

	Optional<User> findByUserName(String string);

	List<User> findByIsEmailVerified(boolean b);

//	List<User> findByIsDeleted(boolean b);

}
