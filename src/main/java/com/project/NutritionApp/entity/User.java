package com.project.NutritionApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


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

    @Column(nullable = false, unique = true)
    private String email; // Kullanıcı Adı

    private int age; // Yaş

    private double weight; // Kilo

    private double height; // Boy

    private String gender;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String activityLevel; // Kullanıcının Aktivite Seviyesi
    private String dietGoal; // Kullanıcının Diyet Hedefi
    private Double targetWeight; // Kullanıcının Hedef Ağırlığı
}
