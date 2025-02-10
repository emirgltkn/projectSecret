package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.request.LoginRequest;
import com.project.NutritionApp.request.RegisterRequest;
import com.project.NutritionApp.response.AuthResponse;
import com.project.NutritionApp.security.JWTokenProvider;
import com.project.NutritionApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JWTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private static final String UPLOAD_DIR = "src/main/resources/static/media/";


    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JWTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        Optional<User> user = userService.getUserByUserName(loginRequest.getUserName());

        if (user.isPresent()) {
            User readyUser = user.get();
            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken("Bearer " + jwtToken);
            authResponse.setUserId(readyUser.getUserId());
            authResponse.setProfilePicture(readyUser.getProfilePhoto());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> register(
            @RequestParam("userName") String userName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "photo", required = false) MultipartFile profileImage) {

        System.out.println("sıkıntı burda mı 1 ");
        AuthResponse authResponse = new AuthResponse();
        if (userService.getUserByUserName(userName).isPresent() ||
                userService.getUserByEmail(email).isPresent()) {
            System.out.println("sıkıntı burda mı");
            authResponse.setMessage("Username or Email already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
        System.out.println("sıkıntı burda mı 2 ");
        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));

        if (profileImage != null && !profileImage.isEmpty()) {
            System.out.println("sıkıntı burda mı 3 ");
            // Fotoğrafın ismini veya yolunu alalım
            String fileName = System.currentTimeMillis() + "_" + profileImage.getOriginalFilename(); // Benzersiz bir dosya adı oluşturuyoruz
            try {
                System.out.println("sıkıntı burda mı 4 ");
                // Fotoğrafı kaydediyoruz
                Path path = Paths.get("uploads/" + fileName);
                Files.write(path, profileImage.getBytes()); // Fotoğrafı diske kaydediyoruz
                user.setProfilePhoto(fileName);
            } catch (IOException e) {
                System.out.println("sıkıntı burda mı 5 ");
                e.printStackTrace();
            }
        }

        User savedUser = userService.saveOneUser(user);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(savedUser.getUserName(), password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        System.out.println("sıkıntı burda mı 6 ");
        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setUserId(savedUser.getUserId());
        authResponse.setProfilePicture(savedUser.getProfilePhoto());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
