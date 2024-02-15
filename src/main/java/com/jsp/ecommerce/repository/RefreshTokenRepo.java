package com.jsp.ecommerce.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecommerce.entity.AccessToken;
import com.jsp.ecommerce.entity.RefreshToken;
import com.jsp.ecommerce.entity.User;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

Optional<RefreshToken> findByToken(String refreshToken);

	List<RefreshToken> findAllByUserAndIsBlockedAndTokenNot(User user, boolean b, String accessToken);

	List<RefreshToken> findAllByUserAndIsBlocked(User user, boolean b);
	List<RefreshToken> findByExpirationBefore(LocalDateTime now);



}
