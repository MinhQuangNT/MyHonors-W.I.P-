package com.example.honorsserver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private static final int MAX_POST_LENGTH = 256;
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("MMM d, h:mm a", Locale.getDefault());

    private final PostsRepository postsRepository;
    public PostController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postsRepository.findAllByOrderByCreatedAtDesc().stream().map(this::toResponse).toList();
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody CreatePostRequest request) {
        if (request == null || request.content() == null || request.content().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post content can't be empty.");
        }

        String content = request.content().trim();
        if (content.length() > MAX_POST_LENGTH) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Post content can't exceed " + MAX_POST_LENGTH + " characters."
            );
        }

        PostEntity savedPost = postsRepository.save(new PostEntity(
                "Anonymous",
                content,
                LocalDateTime.now(),
                false
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(savedPost));
    }

    private PostResponse toResponse(PostEntity post) {
        return new PostResponse(
                post.getId().toString(),
                post.getAuthorName(),
                post.getContent(),
                post.getCreatedAt().format(TIMESTAMP_FORMAT),
                post.isFromProfessor()
        );
    }
}