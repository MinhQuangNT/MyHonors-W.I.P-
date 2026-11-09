package com.example.honorsserver;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String authorName;

    @Column(nullable = false, length = 256)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_from_professor", nullable = false)
    private boolean fromProfessor;

    // Constructor to load database rows.
    protected PostEntity() {
    }

    public PostEntity(
            String authorName,
            String content,
            LocalDateTime createdAt,
            boolean fromProfessor
    ) {
        this.authorName = authorName;
        this.content = content;
        this.createdAt = createdAt;
        this.fromProfessor = fromProfessor;
    }

    public UUID getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isFromProfessor() {
        return fromProfessor;
    }
}