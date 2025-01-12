package com.project.NutritionApp.service;

import com.project.NutritionApp.dto.Food;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FoodService {

    public List<Food> getAllFoods() {
        // Veritabanı yerine şu an için sabit veriler döneceğiz
        return Arrays.asList(
                new Food("Apple", 50),
                new Food("Banana", 100)
        );
    }
}
