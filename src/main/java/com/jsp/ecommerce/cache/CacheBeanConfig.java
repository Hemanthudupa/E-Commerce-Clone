package com.jsp.ecommerce.cache;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jsp.ecommerce.entity.User;

@Configuration
public class CacheBeanConfig {

	@Bean
	CacheStore<User> cahceStore() {
		return new CacheStore<User>(Duration.ofMinutes(5));
	}

	@Bean
	CacheStore<String> otpCahceStore() {
		return new CacheStore<String>(Duration.ofMinutes(1));
	}

}
