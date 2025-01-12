package com.project.NutritionApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "food_logs")
@Data
public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // FoodLog ID

    @Column(nullable = false)
    private String foodName; // Yiyecek Adı

    private int calorie; // Kalori

    private int protein; // Protein miktarı (gram)

    private int fat; // Yağ miktarı (gram)

    private int carbohydrate; // Karbonhidrat miktarı (gram)

    // Kullanıcı ile ilişkilendirme (ManyToOne ilişkisi)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Hangi kullanıcıya ait olduğu
}
