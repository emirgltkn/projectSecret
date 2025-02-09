package com.project.NutritionApp.repository;

import com.project.NutritionApp.entity.Post;
import com.project.NutritionApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
