package com.jsp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.AccessToken;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long> {

}