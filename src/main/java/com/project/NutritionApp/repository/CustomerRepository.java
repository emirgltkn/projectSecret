package com.project.NutritionApp.repository;

import com.project.NutritionApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerRepository, Long>{

    Optional<Customer> findByCustomerName(String customerName);

}