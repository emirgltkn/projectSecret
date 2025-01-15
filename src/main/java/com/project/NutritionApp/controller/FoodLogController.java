package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.FoodLog;
import com.project.NutritionApp.service.FoodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foodlogs")
public class FoodLogController {

    @Autowired
    private FoodLogService foodLogService;

    @GetMapping("/user/{userId}")
    public List<FoodLog> getFoodLogsByUserIdAndDate(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createDate) {
        return foodLogService.getFoodLogsByUserIdAndDate(userId, createDate);
    }


    @GetMapping("/lala")
    public String deneme() {
        return foodLogService.deneme(); // Service'den veri alıyoruz
    }

    @PostMapping
    public FoodLog createFoodLog(@RequestBody FoodLog foodLog) {
        return foodLogService.saveFoodLog(foodLog); // Yeni FoodLog kaydını alıyoruz
    }

    @DeleteMapping("/user/{userId}/food/{foodId}")
    public ResponseEntity<String> deleteFoodLog(
            @PathVariable Long userId,
            @PathVariable Long foodId) {
        foodLogService.deleteFoodLog(userId, foodId);
        return ResponseEntity.ok("Food log deleted successfully");
    }

    @PutMapping("/user/{userId}/food/{foodId}")
    public ResponseEntity<FoodLog> updateFoodLog(
            @PathVariable Long userId,
            @PathVariable Long foodId,
            @RequestBody FoodLog updatedFoodLog) {
        FoodLog updatedLog = foodLogService.updateFoodLog(userId, foodId, updatedFoodLog);
        return ResponseEntity.ok(updatedLog);
    }



}
