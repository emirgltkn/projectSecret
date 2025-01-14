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
    private String userName; // Kullanıcı Adı

    @Column(nullable = false)
    private String password; // Şifre

    private int age; // Yaş

    private double weight; // Kilo

    private double height; // Boy
}
