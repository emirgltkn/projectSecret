package com.project.NutritionApp.repository;

import com.project.NutritionApp.entity.FoodLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {

    // Kullanıcı ID'sine göre FoodLog'ları al
    List<FoodLog> findByUser_UserId(Long userId); // User sınıfındaki 'userId' alanına göre sorgulama yapıyoruz
}
