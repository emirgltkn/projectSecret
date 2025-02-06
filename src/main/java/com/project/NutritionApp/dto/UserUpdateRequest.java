package com.project.NutritionApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter
public class UserUpdateRequest {



    private int age; // Yaş

    private double weight; // Kilo

    private double height; // Boy

    private String gender;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String activityLevel; // Kullanıcının Aktivite Seviyesi
    private String dietGoal; // Kullanıcının Diyet Hedefi
    private Double targetWeight; // Kullanıcının Hedef Ağırlığı
}

