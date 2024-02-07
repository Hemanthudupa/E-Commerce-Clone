package com.jsp.ecommerce.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jsp.ecommerce.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduleJob {

	@Autowired
	private UserRepo userRepo;

	@Scheduled(fixedDelay = 20000L)
	public void deleteUsers() {
//		log.info("hello ");
		userRepo.findByIsEmailVerified(false).forEach(user -> userRepo.delete(user));
	}
}