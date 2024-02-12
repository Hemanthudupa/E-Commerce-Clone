package com.jsp.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.AccessToken;
import com.jsp.ecommerce.entity.User;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long> {

	Optional<AccessToken> findByToken(String accessToken);

	List<AccessToken> findAllByUserAndIsBlockedAndTokenNot(User user, boolean b, String accessToken);

	List<AccessToken> findAllByUserAndIsBlocked(User user, boolean b);

}
