package com.ray.ecommerce.service;

import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.exception.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    User findUserByUsername(String username);

    User register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException;

    User addNewUser(String firstName, String lastName, String username, String email, String[] role, boolean isNonLocked,
                    boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail,
                    String[] role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateProfileImage(String username, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    void resetPassword(String email) throws EmailNotFoundException;

    void deleteUser(long id) throws UserNotFoundException, IOException;
}
