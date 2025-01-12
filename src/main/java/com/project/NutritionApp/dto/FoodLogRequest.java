package com.project.NutritionApp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class FoodLogRequest {
    private String foodName;
    private Double calorie;
    private Double protein;
    private Double fat;
    private Double carbohydrate;
    private Date createDate;
    private Long userId;
}
