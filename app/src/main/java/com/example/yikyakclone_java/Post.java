package com.example.yikyakclone_java;

public class Post {
    private String id;
    private String authorName;
    private String content;
    private String timestamp;
    private boolean isFromProfessor;

    public Post() {}

    public Post(String authorName, String content, String timestamp,
                boolean isFromProfessor) {
        this.authorName = authorName;
        this.content = content;
        this.timestamp = timestamp;
        this.isFromProfessor = isFromProfessor;
    }

    public String getId() {
        return id;
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