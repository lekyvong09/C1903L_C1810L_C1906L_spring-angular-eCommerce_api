package com.ray.ecommerce.controller;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.constant.SecurityConstant;
import com.ray.ecommerce.dao.UserRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.domain.UserPrincipal;
import com.ray.ecommerce.entity.PageInfo;
import com.ray.ecommerce.exception.*;
import com.ray.ecommerce.service.UserService;
import com.ray.ecommerce.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private JWTTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
                          UserRepository userRepository) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        // authenticate user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        // create userPrincipal
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);

        // return JSON WebToken
        HttpHeaders jwtTokenHeader = new HttpHeaders();
        jwtTokenHeader.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));

        return new ResponseEntity<>(loginUser, jwtTokenHeader, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws EmailExistException, UsernameExistException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    // display profile Image
    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName)
            throws IOException {
        // /user/ray/springAngularEcommerce2/users/
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + username + "/" + fileName));
    }

    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
        // http://localhost:8080/api/user/image/profile/{username}  => https://robohash.org/{username}
        URL url = new URL(FileConstant.TEMP_PROFILE_IMAGE_BASE_URL + username);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            byte[] chunk = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @PostMapping("/add")
    public  ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                            @RequestParam("lastName") String lastName,
                                            @RequestParam("username") String username,
                                            @RequestParam("email") String email,
                                            @RequestParam("role") Set<String> role,
                                            @RequestParam("isActive") String isActive,
                                            @RequestParam("isNonLocked") String isNonLocked,
                                            @RequestParam(value="profileImage", required = false) MultipartFile profileImage)
            throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {

        User newUser = userService.addNewUser(firstName, lastName, username, email, role.toArray(new String[0]),
                Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/update")
    public  ResponseEntity<User> updateUser(@RequestParam("currentUsername") String currentUsername,
                                            @RequestParam("firstName") String firstName,
                                            @RequestParam("lastName") String lastName,
                                            @RequestParam("username") String username,
                                            @RequestParam("email") String email,
                                            @RequestParam("role") Set<String> role,
                                            @RequestParam("isActive") String isActive,
                                            @RequestParam("isNonLocked") String isNonLocked,
                                            @RequestParam(value="profileImage", required = false) MultipartFile profileImage)
            throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {

        User updatedUser = userService.updateUser(currentUsername, firstName, lastName, username, email, role.toArray(new String[0]),
                Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(@RequestParam("profileImage") MultipartFile profileImage)
            throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue= "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(paging);

        PageInfo myPage = new PageInfo(users.getNumber(), users.getTotalElements(), users.getTotalPages(), users.getSize());

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("page", myPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('user:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException, IOException {
        userService.deleteUser(id);
        return response(HttpStatus.OK, "User has been deleted successfully");
    }

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException {
        userService.resetPassword(email);
        return response(HttpStatus.OK, "The reset password has been sent to email " + email);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
