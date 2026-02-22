package com.example.jparelation.post.repository;

import com.example.jparelation.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
