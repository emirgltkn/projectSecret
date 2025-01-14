package com.project.NutritionApp.service;

import com.project.NutritionApp.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.project.NutritionApp.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
@Service
public class UserService {

    UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


}