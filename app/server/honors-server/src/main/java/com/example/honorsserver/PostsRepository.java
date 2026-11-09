package com.example.honorsserver;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostEntity, UUID> {
    List<PostEntity> findAllByOrderByCreatedAtDesc();
}