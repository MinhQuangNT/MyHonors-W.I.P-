package com.example.yikyakclone_java;

public class CreatePostRequest {
    private final String content;

    public CreatePostRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}