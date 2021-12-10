package com.ray.ecommerce.service;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.dao.RoleRepository;
import com.ray.ecommerce.dao.UserRepository;
import com.ray.ecommerce.domain.Authority;
import com.ray.ecommerce.domain.Role;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.domain.UserPrincipal;
import com.ray.ecommerce.exception.EmailExistException;
import com.ray.ecommerce.exception.NotAnImageFileException;
import com.ray.ecommerce.exception.UsernameExistException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
@Qualifier("myUserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        User user = new User();
        user.setUserId(RandomStringUtils.randomNumeric(10));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRoles(Stream.of(roleRepository.findByName("ROLE_USER_READ")).collect(Collectors.toSet()));
        user.setAuthorities(roleRepository.findByName("ROLE_USER_READ").getAuthorities().stream().distinct().collect(Collectors.toSet()));
        user.setProfileImageUrl(
            // http://localhost:8080/api/user/image/profile/{username}  => https://robohash.org/{username}
            ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_USER_IMAGE_PATH + username).toUriString()
        );

        User newUser = userRepository.save(user);
        LOGGER.info("New user password: " + password);
        return newUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found by username: " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }


    @Override
    public User addNewUser(String firstName, String lastName, String username, String email, String[] role,
                           boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        User user = new User();
        user.setUserId(RandomStringUtils.randomNumeric(10));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(isActive);
        user.setNotLocked(isNonLocked);
        user.setRoles(Arrays.stream(role).map(r -> roleRepository.findByName(r)).collect(Collectors.toSet()));
        user.setAuthorities(Arrays.stream(role).map(r -> roleRepository.findByName(r))
                .flatMap(ro -> ro.getAuthorities().stream()).collect(Collectors.toSet()));
        user.setProfileImageUrl(
                // http://localhost:8080/api/user/image/profile/{username}  => https://robohash.org/{username}
                ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_USER_IMAGE_PATH + username).toUriString()
        );

        userRepository.save(user);
        LOGGER.info("New user password: " + password);

        // http://localhost:8080/api/user/image/{username}/{fileName.jpg} => /user/ray/springAngularEcommerce/users/{username}/{fileName.jpg}
        saveProfileImage(user, profileImage);

        return user;
    }

    private void saveProfileImage(User user, MultipartFile profileImage) throws NotAnImageFileException, IOException {
        if (profileImage != null) {
            if (!Arrays.asList(MimeTypeUtils.IMAGE_JPEG_VALUE, MimeTypeUtils.IMAGE_GIF_VALUE, MimeTypeUtils.IMAGE_PNG_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + " is not an image file");
            }

            // /user/ray/springAngularEcommerce2/users/
            Path userFolder = Paths.get(FileConstant.USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }

            // delete if exist /user/ray/springAngularEcommerce2/users/{username}/{fileName.jpg}
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + "." + "jpg"));

            // save
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + "." + "jpg"), REPLACE_EXISTING);

            // update new URL http://localhost:8080/api/user/image/{username}/{fileName.jpg}
            user.setProfileImageUrl(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(FileConstant.USER_IMAGE_PATH + user.getUsername() + "/" + user.getUsername() + "." + "jpg")
                    .toUriString()
            );
            userRepository.save(user);
            LOGGER.info("Save profile image: " + profileImage.getOriginalFilename());
        }
    }


    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws EmailExistException, UsernameExistException {

        User newUserByUsername = userRepository.findUserByUsername(newUsername);
        User newUserByEmail = userRepository.findUserByEmail(newEmail);

        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = userRepository.findUserByUsername(currentUsername);

            if (currentUser == null) {
                throw new UsernameNotFoundException("No user found by user " + currentUsername);
            }
            if (newUserByUsername != null && !currentUser.getId().equals(newUserByUsername.getId())) {
                throw new UsernameExistException("Username already exist");
            }
            if (newUserByEmail != null && !currentUser.getId().equals(newUserByEmail.getId())) {
                throw new EmailExistException("Email already exist");
            }
            return currentUser;
        } else {
            if (newUserByUsername != null) {
                throw new UsernameExistException("Username already exist");
            }
            if (newUserByEmail != null) {
                throw new EmailExistException("Email already exist");
            }
            return null;
        }
    }


}
