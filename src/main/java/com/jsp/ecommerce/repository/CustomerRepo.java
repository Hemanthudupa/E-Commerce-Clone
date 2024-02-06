package com.jsp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
