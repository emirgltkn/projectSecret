package com.project.NutritionApp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class FoodLogDto {
    private String title;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
    private Double grams;
}
