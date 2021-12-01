package com.ray.ecommerce.service;

import com.ray.ecommerce.domain.User;

public interface UserService {

    User findUserByUsername(String username);
}
