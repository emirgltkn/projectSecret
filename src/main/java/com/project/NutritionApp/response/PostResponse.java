package com.project.NutritionApp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {

    private Long id;
    private Long userId;  // Sadece userId'yi alıyoruz
    private String text;
    private String imageUrl;
    private String createdAt;

    public PostResponse(Long id, Long userId, String text, String imageUrl, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }
}
