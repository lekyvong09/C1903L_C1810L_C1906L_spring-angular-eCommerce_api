package com.ray.ecommerce.controller;

import com.ray.ecommerce.constant.SecurityConstant;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.domain.UserPrincipal;
import com.ray.ecommerce.service.UserService;
import com.ray.ecommerce.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private JWTTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
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
}
