package com.ray.ecommerce.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    private LoadingCache<String, Integer> loginAttemptCache;

    private static final int ATTEMPTS_INCREMENT = 1;
    private static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 5;

    public LoginAttemptService() {
        loginAttemptCache = CacheBuilder.newBuilder()
                                .expireAfterWrite(15, TimeUnit.MINUTES)
                                .maximumSize(100)
                                .build(new CacheLoader<String, Integer>() {
                                    @Override
                                    public Integer load(String key) throws Exception {
                                        return 0;
                                    }
                                });
    }

    // add user to the cache if fail log in
    public void addUserToLoginAttemptCache(String username) {
        int attempts = 0;
        try {
            attempts = loginAttemptCache.get(username) + ATTEMPTS_INCREMENT;
            loginAttemptCache.put(username, attempts);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // evict if you login successful
    public void evictUserFromLoginAttemptCache(String username) {
        loginAttemptCache.invalidate(username);
    }

    public boolean hasExceededMaxAttempts(String username) throws ExecutionException {
        return loginAttemptCache.get(username) >= MAXIMUM_NUMBER_OF_ATTEMPTS;
    }

}
