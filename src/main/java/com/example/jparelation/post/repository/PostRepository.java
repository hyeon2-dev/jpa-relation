package com.example.jparelation.post.repository;

import com.example.jparelation.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
