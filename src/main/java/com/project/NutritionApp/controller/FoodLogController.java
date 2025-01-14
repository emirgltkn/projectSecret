package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.FoodLog;
import com.project.NutritionApp.service.FoodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodlogs")
public class FoodLogController {

    @Autowired
    private FoodLogService foodLogService;

    @GetMapping("/user/{userId}")
    public List<FoodLog> getFoodLogsByUserId(@PathVariable Long userId) {
        return foodLogService.getFoodLogsByUserId(userId); // Service'den veri alıyoruz
    }

    @GetMapping("/lala")
    public String deneme() {
        return foodLogService.deneme(); // Service'den veri alıyoruz
    }

    @PostMapping
    public FoodLog createFoodLog(@RequestBody FoodLog foodLog) {
        return foodLogService.saveFoodLog(foodLog); // Yeni FoodLog kaydını alıyoruz
    }
}
