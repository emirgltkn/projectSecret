package com.project.NutritionApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Kullanıcı ID'si

    @Column(nullable = false, unique = true)
    private String kullaniciAdi; // Kullanıcı Adı

    @Column(nullable = false)
    private String sifre; // Şifre

    private int yas; // Yaş

    private double kilo; // Kilo

    private double boy; // Boy
}
