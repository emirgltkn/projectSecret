package com.project.NutritionApp.controller;

import com.project.NutritionApp.dto.UserUpdateRequest;
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
            userResponseObject.setHeight(readyUser.getHeight());
            userResponseObject.setGender(readyUser.getGender());
            userResponseObject.setActivityLevel(readyUser.getActivityLevel());
            userResponseObject.setDietGoal(readyUser.getDietGoal());
            userResponseObject.setTargetWeight(readyUser.getTargetWeight());
             return new ResponseEntity<>(userResponseObject, HttpStatus.OK);
        }
        else {
            throw new Exception("User not found");

        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserUpdateRequest updateRequest) {

        Optional<User> optionalUser = userService.getUserByUserName(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get(); // Kullanıcı nesnesini al

            // Güncellenmesi gereken alanlar kontrol edilerek değiştirilir
            if (updateRequest.getAge() != 0) {
                user.setAge(updateRequest.getAge());
            }
            if (updateRequest.getGender() != null) {
                user.setGender(updateRequest.getGender());
            }
            if (updateRequest.getWeight() != 0) {
                user.setWeight(updateRequest.getWeight());
            }
            if (updateRequest.getHeight() != 0) {
                user.setHeight(updateRequest.getHeight());
            }
            if (updateRequest.getActivityLevel() != null) {
                user.setActivityLevel(updateRequest.getActivityLevel());
            }
            if (updateRequest.getBirthDate() != null) {
                user.setBirthDate(updateRequest.getBirthDate());
            }
            if (updateRequest.getDietGoal() != null) {
                user.setDietGoal(updateRequest.getDietGoal());
            }
            if (updateRequest.getTargetWeight() != null) {
                user.setTargetWeight(updateRequest.getTargetWeight());
            }

            userService.saveOneUser(user); // Güncellenmiş User nesnesini kaydet

            return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


}


