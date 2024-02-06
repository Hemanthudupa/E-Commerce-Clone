package com.jsp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.Seller;

public interface SellerRepo extends JpaRepository<Seller, Integer> {

}
