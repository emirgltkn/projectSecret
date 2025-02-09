package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.Post;
import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.request.PostRequest;
import com.project.NutritionApp.response.PostResponse;
import com.project.NutritionApp.service.PostService;
import com.project.NutritionApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        User user = userService.getOneUserById(postRequest.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        // PostService'deki createPost metodunu kullanıyoruz
        Post savedPost = postService.createPost(postRequest.getUserId(), postRequest.getText(), postRequest.getImageUrl());
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();  // PostService'den tüm postları alıyoruz
        List<PostResponse> postResponses = posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getUser().getUserId(),  // UserId'yi alıyoruz
                        post.getText(),
                        post.getImageUrl(),
                        post.getCreatedAt().toString()
                ))
                .collect(Collectors.toList());  // Listeye dönüştürüyoruz
        return ResponseEntity.ok(postResponses);  // 200 OK ile döndürüyoruz
    }
}


