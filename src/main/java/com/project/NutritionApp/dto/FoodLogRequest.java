package com.project.NutritionApp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class FoodLogRequest {
    private String foodName;
    private Double calorie;
    private Double protein;
    private Double fat;
    private Double carbohydrate;
    private LocalDate createDate;
    private Long userId;
    private Double portion;
}
