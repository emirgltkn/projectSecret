package com.project.NutritionApp.service;

import com.project.NutritionApp.entity.Post;
import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.repository.PostRepository;
import com.project.NutritionApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(Long userId, String content, String image) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = new Post();
        post.setUser(user);
        post.setText(content);
        post.setImageUrl(image);
        return postRepository.save(post);
    }
    public Post savePost(Post post) {
        return postRepository.save(post);  // Post nesnesini veritabanına kaydeder
    }
    public List<Post> getAllPosts() {
        return postRepository.findAll();  // Veritabanındaki tüm postları getir
    }
}
