package com.jsp.ecommerce.util;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jsp.ecommerce.repository.AccessTokenRepo;
import com.jsp.ecommerce.repository.RefreshTokenRepo;
import com.jsp.ecommerce.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduleJob {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AccessTokenRepo accessTokenRepo;

	@Autowired
	private RefreshTokenRepo refreshTokenRepo;

//	@Scheduled(fixedDelay = 20000L)
//	public void deleteUsers() {
////		log.info("hello ");
//		userRepo.findByIsEmailVerified(false).forEach(user -> userRepo.delete(user));
//	}

//	@Scheduled(fixedDelay = 20000L)
//	public void deleteExpireAccessTokens() {
//		accessTokenRepo.deleteAll(accessTokenRepo.findByExpirationBefore(LocalDateTime.now()));
//	}
//
//	@Scheduled(fixedDelay = 20000L)
//	public void deleteExpireRefreshTokens() {
//		refreshTokenRepo.deleteAll(refreshTokenRepo.findByExpirationBefore(LocalDateTime.now()));
//	}

	
}