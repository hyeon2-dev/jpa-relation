package com.example.jparelation.post.controller;

import com.example.jparelation.post.dto.postDto.PostRequestDto;
import com.example.jparelation.post.dto.postDto.PostResponseDto;
import com.example.jparelation.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost (@RequestBody PostRequestDto dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts () {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPost (@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> updatePost (
            @PathVariable Long postId,
            @RequestBody PostRequestDto dto
    ) {
        return ResponseEntity.ok(postService.updatePost(postId, dto));
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost (@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
