package com.project.NutritionApp.request;
import lombok.Data;

@Data
public class RegisterRequest {
    String email;
    String userName;
    String password;

}