package com.example.honorsserver;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @GetMapping
    public List<PostResponse> getPosts() {
        return List.of(
                new PostResponse("1", "Griffin Nye", "I love java!", "Today, 9:00 AM", true),
                new PostResponse("2", "Quang", "There's an impostor among us.", "Today, 10:15 AM", false)
        );
    }
}