package com.project.NutritionApp.service;

import com.project.NutritionApp.entity.Customer;
import com.project.NutritionApp.repository.CustomerRepository;
import org.apache.el.lang.ELSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {

        Optional<Customer> customer=  customerRepository.findByCustomerName(username);
        if (customer.isPresent()) {
            var customerObj = customer.get();
            return User.builder()
                    .username(customerObj.getCustomerName())
                    .password(customerObj.getPassword())
                    .build();
        }

        else{
            return null;
                }
    }

    }