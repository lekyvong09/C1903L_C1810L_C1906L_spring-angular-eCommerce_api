package com.ray.ecommerce.service;

import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.exception.EmailExistException;
import com.ray.ecommerce.exception.UsernameExistException;

public interface UserService {

    User findUserByUsername(String username);

    User register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException;
}
