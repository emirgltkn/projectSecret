package com.project.NutritionApp.controller;

import com.project.NutritionApp.dto.FoodLogDto;
import com.project.NutritionApp.entity.FoodLog;
import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.request.CreateFoodLogRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/foodlogs")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodLogController {

    @Autowired
    private FoodLogService foodLogService;

    @Autowired
    private UserService userService;

    /**
     * Kullanıcının belirli bir tarihte yediği yemekleri getirir.
     */
    @GetMapping
    public ResponseEntity<List<FoodLog>> getFoodLogsByDate(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createDate) {

        Optional<User> user = userService.getUserByUserName(userDetails.getUsername());

        if (user.isPresent()) {
            List<FoodLog> foodLogs = foodLogService.getFoodLogsByUserIdAndDate(user.get().getUserId(), createDate);
            return ResponseEntity.ok(foodLogs);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Kullanıcının yediği yeni bir yemeği kaydeder.
     */

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<?> createFoodLog(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateFoodLogRequest request) {

        System.out.println("buraya girdik mi önemli");
        System.out.println(request.getDate());
        System.out.println(request.getMeal());
        Optional<User> user = userService.getUserByUserName(userDetails.getUsername());
        if (user.isPresent()) {

            List<FoodLog> list = new ArrayList<>();

            for ( FoodLogDto obj : request.getMeal()) {
                FoodLog foodlog = new FoodLog();
                foodlog.setUser(user.get());
                foodlog.setCarbs(obj.getCarbs());
                foodlog.setProtein(obj.getProtein());
                foodlog.setTitle(obj.getTitle());
                foodlog.setFat(obj.getFat());
                foodlog.setCalories(obj.getCalories());
                foodlog.setCreateDate(request.getDate());
                foodlog.setGrams(obj.getGrams());
                list.add(foodlog);
            }


           foodLogService.saveFoodLogs(list);
            return new ResponseEntity<>("Foodlogs created!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Foodlogs NOT CREATED!", HttpStatus.BAD_REQUEST);
    }

    /**
     * Kullanıcının bir yemeği güncellemesini sağlar.
     */
/*
    @PutMapping("/{foodId}")
    public ResponseEntity<FoodLog> updateFoodLog(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long foodId,
            @RequestBody FoodLog updatedFoodLog) {

        Optional<User> user = userService.getUserByUserName(userDetails.getUsername());

        if (user.isPresent()) {
            FoodLog updatedLog = foodLogService.updateFoodLog(user.get().getUserId(), foodId, updatedFoodLog);
            return ResponseEntity.ok(updatedLog);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
*/

    /**
     * Kullanıcının bir yemeği silmesini sağlar.
     */
    @DeleteMapping("/{foodId}")
    public ResponseEntity<String> deleteFoodLog(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long foodId) {

        Optional<User> user = userService.getUserByUserName(userDetails.getUsername());

        if (user.isPresent()) {
            foodLogService.deleteFoodLog(user.get().getUserId(), foodId);
            return ResponseEntity.ok("Food log deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
