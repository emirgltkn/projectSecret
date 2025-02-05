package com.project.NutritionApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "food_logs")
@Data

public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId; // FoodLog ID

    private int id;

    @Column(nullable = false)
    private String foodName; // Yiyecek Adı

    private double calorie; // Kalori

    private double protein; // Protein miktarı (gram)

    private double fat; // Yağ miktarı (gram)

    private double carbohydrate; // Karbonhidrat miktarı (gram)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    private double portion;

    // Kullanıcı ile ilişkilendirme (ManyToOne ilişkisi)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false) // Burada userId kullanıyoruz

    private User user; // Hangi kullanıcıya ait olduğu

    @Version
    private int version; // Optimistic Locking için version alanı
}
