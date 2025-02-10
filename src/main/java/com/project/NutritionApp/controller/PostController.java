package com.project.NutritionApp.controller;

import com.project.NutritionApp.entity.Post;
import com.project.NutritionApp.entity.User;
import com.project.NutritionApp.response.ListPostResponse;
import com.project.NutritionApp.response.PostDto;
import com.project.NutritionApp.service.PostService;
import com.project.NutritionApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam("content") String content,
                                           @RequestParam(value = "image", required = false) MultipartFile image) {

        Optional<User> optionalUser = userService.getUserByUserName(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (image != null && !image.isEmpty()) {
                System.out.println("sıkıntı burda mı 3 ");
                // Fotoğrafın ismini veya yolunu alalım
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // Benzersiz bir dosya adı oluşturuyoruz
                try {
                    System.out.println("sıkıntı burda mı 4 ");
                    // Fotoğrafı kaydediyoruz
                    Path path = Paths.get("uploads/" + fileName);
                    Files.write(path, image.getBytes()); // Fotoğrafı diske kaydediyoruz
                    Post savedPost = postService.createPost(user.getUserId(), content, fileName);
                    return ResponseEntity.ok(savedPost);
                } catch (IOException e) {
                    System.out.println("sıkıntı burda mı 5 ");
                    e.printStackTrace();
                }
            }

            // PostService'deki createPost metodunu kullanıyoruz


        }
        return  null;
    }

    @GetMapping
    public ResponseEntity<ListPostResponse> getAllPosts(@AuthenticationPrincipal UserDetails userDetails) {
        ListPostResponse sendObj = new ListPostResponse();

        List<Post> posts = postService.getAllPosts();  // PostService'den tüm postları alıyoruz
        List<PostDto> postRespons = posts.stream()
                .map(post -> new PostDto(
                        post.getUser().getUserName(),
                        post.getText(),
                        post.getImageUrl(),  // UserId'yi alıyoruz
                        post.getCreatedAt(),
                        post.getUser().getProfilePhoto()
                ))
                .collect(Collectors.toList());  // Listeye dönüştürüyoruz
        sendObj.setPostDtos(postRespons);

        return ResponseEntity.ok(sendObj);  // 200 OK ile döndürüyoruz
    }
}


