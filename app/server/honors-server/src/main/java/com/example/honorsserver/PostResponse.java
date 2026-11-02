package com.example.honorsserver;

public record PostResponse(
        String id,
        String authorName,
        String content,
        String timestamp,
        boolean isFromProfessor
) {}