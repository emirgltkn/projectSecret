package com.project.NutritionApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId; // Kullanıcı ID'si

    @Column(nullable = false, unique = true)
    private String customerName; // Kullanıcı Adı

    @Column(nullable = false)
    private String password; // Şifre

    private int age; // Yaş

    private double weight; // Kilo

    private double height; // Boy
}
