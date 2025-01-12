package com.project.NutritionApp.service;

import com.project.NutritionApp.entity.FoodLog;
import com.project.NutritionApp.repository.FoodLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodLogService {

    @Autowired
    private FoodLogRepository foodLogRepository;

    public List<FoodLog> getFoodLogsByUserId(Long userId) {
        return foodLogRepository.findByUser_UserId(userId); // User'dan gelen userId'yi kullanıyoruz
    }

    public FoodLog saveFoodLog(FoodLog foodLog) {
        return foodLogRepository.save(foodLog);
    }
}
