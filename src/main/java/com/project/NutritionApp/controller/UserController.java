package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.FoodLog;
import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.response.UserResponse;
import com.project.NutritionApp.service.FoodLogService;
import com.project.NutritionApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        User readyUser = new User();
        System.out.println("burda");
        System.out.println(userDetails.getUsername());
        System.out.println("noluyo");
        Optional<User> user = userService.getUserByUserName(userDetails.getUsername());
        if (user.isPresent()) {

            readyUser = user.get();
            UserResponse userResponseObject = new UserResponse();
            userResponseObject.setUserName(readyUser.getUserName());
            userResponseObject.setAge(readyUser.getAge());
            userResponseObject.setEmail(readyUser.getEmail());
            userResponseObject.setWeight(readyUser.getWeight());
             return new ResponseEntity<>(userResponseObject, HttpStatus.OK);
        }
        else {
            throw new Exception("User not found");

        }

    }
    }