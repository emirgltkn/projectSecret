package com.project.NutritionApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Food {
    // Getter ve Setter'lar
    private String name;
    private int calories;

    // Constructor
    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

}
