package com.example.jparelation.post.controller;

import com.example.jparelation.post.dto.CommentDto.CommentRequestDto;
import com.example.jparelation.post.dto.CommentDto.CommentResponseDto;
import com.example.jparelation.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto
    ) {
        return ResponseEntity.ok(commentService.updateComment(commentId, dto));
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }


}
