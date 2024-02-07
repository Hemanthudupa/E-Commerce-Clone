package com.jsp.ecommerce.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jsp.ecommerce.repository.UserRepo;

@Component
public class ScheduleJob {

	@Autowired
	private UserRepo userRepo;

//	@Scheduled(fixedDelay = 2000L)
//	public void deleteUsers() {
//		userRepo.findByIsEmailVerified(false).forEach(user -> user.setDeleted(true));
//	}

}
