package com.jsp.ecommerce.cache;

import java.time.Duration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheStore<T> {

	private Cache<String, T> cache;

	public CacheStore(Duration expiry) {
		this.cache = CacheBuilder.newBuilder().expireAfterWrite(expiry)
				.concurrencyLevel(Runtime.getRuntime().availableProcessors()).build();
	}

	public void add(String key, T value) {
		cache.put(key, value);
	}

	public T get(String key) {
		return cache.getIfPresent(key);
	}

	public void remove(String key) {
		cache.invalidate(key);                                                                      // it will remove the value of the cache based on the key which we provide
	}
 
}
