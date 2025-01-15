package com.project.NutritionApp.service;

import com.project.NutritionApp.entity.FoodLog;
import com.project.NutritionApp.repository.FoodLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FoodLogService {

    @Autowired
    private FoodLogRepository foodLogRepository;

    public List<FoodLog> getFoodLogsByUserIdAndDate(Long userId, LocalDate createDate) {
        return foodLogRepository.findByUser_UserIdAndCreateDate(userId, createDate);
    }

    public List<FoodLog> getFoodLogsByUserId(Long userId) {
        return foodLogRepository.findByUser_UserId(userId);
    }


    public FoodLog saveFoodLog(FoodLog foodLog) {
        return foodLogRepository.save(foodLog);
    }

    public void deleteFoodLog(Long userId, Long foodId) {
        // Yemeği ID'ye göre bul
        FoodLog foodLog = foodLogRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));

        // Kullanıcının sadece kendi yediği yemeği silebilmesi için kontrol
        if (!foodLog.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("User is not authorized to delete this food log");
        }

        // Silme işlemi
        foodLogRepository.delete(foodLog);
    }

    public Optional<FoodLog> getFoodLogById(Long foodId) {
        return foodLogRepository.findById(foodId);
    }

    public FoodLog updateFoodLog(Long userId, Long foodId, FoodLog updatedFoodLog) {
        // Belirli bir kullanıcıya ait belirli bir yemek kaydını bul
        FoodLog existingFoodLog = foodLogRepository.findByUserAndFoodId(userId,foodId)
                .orElseThrow(() -> new RuntimeException("FoodLog not found"));

        // Kullanıcı doğrulaması
        if (!existingFoodLog.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to update this food log");
        }

        // Güncellenen alanları set et
        existingFoodLog.setFoodName(updatedFoodLog.getFoodName());
        existingFoodLog.setCalorie(updatedFoodLog.getCalorie());
        existingFoodLog.setCarbohydrate(updatedFoodLog.getCarbohydrate());
        existingFoodLog.setProtein(updatedFoodLog.getProtein());
        existingFoodLog.setFat(updatedFoodLog.getFat());
        existingFoodLog.setPortion(updatedFoodLog.getPortion());

        // Veritabanında kaydet
        return foodLogRepository.save(existingFoodLog);
    }



    public String deneme() {
        return "basarili";
    }
}


