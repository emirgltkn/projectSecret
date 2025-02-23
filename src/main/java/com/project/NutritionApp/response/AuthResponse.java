package com.project.NutritionApp.response;

import lombok.Data;

@Data
public class AuthResponse {

    String message;
    Long userId;
    String accessToken;
    String refreshToken;
    String profilePicture;
}