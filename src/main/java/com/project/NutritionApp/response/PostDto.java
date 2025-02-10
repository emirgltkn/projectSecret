package com.project.NutritionApp.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {

    private String userName;  // Sadece userId'yi alıyoruz
    private String content;
    private String imageUrl;

    private LocalDateTime date;
    private String profilePicture;

    public PostDto(String userName, String content, String imageUrl, LocalDateTime date, String profilePicture) {
        this.userName = userName;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.profilePicture = profilePicture;
    }






}
