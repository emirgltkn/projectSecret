package com.project.NutritionApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "food_logs")
@Data

public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId; // FoodLog ID

    @Column(nullable = false)
    private String title; // Yiyecek Adı

    private double calories; // Kalori

    private double protein; // Protein miktarı (gram)

    private double fat; // Yağ miktarı (gram)

    private double carbs; // Karbonhidrat miktarı (gram)

    private double grams;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;


    // Kullanıcı ile ilişkilendirme (ManyToOne ilişkisi)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false) // Burada userId kullanıyoruz
    @JsonIgnoreProperties({"userName", "password", "email", "age", "weight", "height"})
    private User user; // Hangi kullanıcıya ait olduğu

}
