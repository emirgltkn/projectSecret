package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.request.LoginRequest;
import com.project.NutritionApp.request.RegisterRequest;
import com.project.NutritionApp.response.AuthResponse;
import com.project.NutritionApp.security.JWTokenProvider;
import com.project.NutritionApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;

    private JWTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

/*    private RefreshTokenService refreshTokenService;
    */

public AuthController(AuthenticationManager authenticationManager, UserService userService,
                      PasswordEncoder passwordEncoder, JWTokenProvider jwtTokenProvider/*RefreshTokenService refreshTokenService*/) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
    /*this.refreshTokenService = refreshTokenService;*/
}
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        Optional<User> user = userService.getUserByUserName(loginRequest.getUserName());
        if (user.isPresent()) {
            User readyUser = new User();
            readyUser = user.get();
            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken("Bearer " + jwtToken);
            /*     authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));*/
            authResponse.setUserId(readyUser.getUserId());
            return authResponse;
        }
        return null;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        Optional<User> mayErrorControl_1 = userService.getUserByUserName(registerRequest.getUserName());
        if (mayErrorControl_1.isPresent()) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<User> mayErrorControl_2 = userService.getUserByEmail(registerRequest.getEmail());
        if (mayErrorControl_2.isPresent()) {
            authResponse.setMessage("Email already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        System.out.println("geldik mi");
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        System.out.println(registerRequest.getEmail());
        System.out.println(registerRequest.getUserName());
        userService.saveOneUser(user);

/*
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUserName(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
*/

        authResponse.setMessage("User successfully registered.");
       /* authResponse.setAccessToken("Bearer " + jwtToken);*/
    /*    authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));*/
        authResponse.setUserId(user.getUserId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }



}
