package com.jsp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.RefreshToken;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long>{

}