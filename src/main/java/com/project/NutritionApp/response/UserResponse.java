package com.project.NutritionApp.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private String userName;
    private String email;
    private int age;
    private double weight;


    private double height; // Boy

    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String activityLevel; // Kullanıcının Aktivite Seviyesi
    private String dietGoal; // Kullanıcının Diyet Hedefi
    private Double targetWeight; // Kullanıcının Hedef Ağırlığı
}