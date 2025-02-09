package com.project.NutritionApp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private Long userId;
    private String text;
    private String imageUrl;
}
