package com.example.yikyakclone_java;

public class Post {
    private final String authorName;
    private final String content;
    private final String timestamp;
    private final boolean isFromProfessor;

    public Post(String authorName, String content, String timestamp, boolean isFromProfessor) {
        this.authorName = authorName;
        this.content = content;
        this.timestamp = timestamp;
        this.isFromProfessor = isFromProfessor;
    }

    public String getAuthorName() {
        return authorName;
    }
    public String getContent() {
        return content;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public boolean isFromProfessor() {
        return isFromProfessor;
    }
}