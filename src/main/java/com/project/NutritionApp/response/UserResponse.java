package com.project.NutritionApp.response;

import lombok.Data;

@Data
public class UserResponse {

    private String userName;
    private String email;
    private int age;
    private double weight;
}