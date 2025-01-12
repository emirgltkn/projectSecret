package com.project.NutritionApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "food_logs")
@Data
public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // FoodLog ID

    @Column(nullable = false)
    private String foodName; // Yiyecek Adı

    private double calorie; // Kalori

    private double protein; // Protein miktarı (gram)

    private double fat; // Yağ miktarı (gram)

    private double carbohydrate; // Karbonhidrat miktarı (gram)

    private Date createDate;

    // Kullanıcı ile ilişkilendirme (ManyToOne ilişkisi)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false) // Burada userId kullanıyoruz
    private User user; // Hangi kullanıcıya ait olduğu
}
