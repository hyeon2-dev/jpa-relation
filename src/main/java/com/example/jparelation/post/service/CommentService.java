package com.example.jparelation.post.service;

import com.example.jparelation.post.dto.CommentDto.CommentRequestDto;
import com.example.jparelation.post.dto.CommentDto.CommentResponseDto;
import com.example.jparelation.post.entity.Comment;
import com.example.jparelation.post.entity.Post;
import com.example.jparelation.post.repository.CommentRepository;
import com.example.jparelation.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto dto) {
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(
                () -> new IllegalStateException("id에 맞는 Post가 존재하지 않습니다.")
        );

        Comment comment = new Comment(dto.getContent(), post);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getPost().getId()
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponseDto> dtoList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getPost().getId()
            );
            dtoList.add(commentResponseDto);
        }

        return dtoList;
    }

    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("id가 맞는 Comment가 존재하지 않습니다.")
        );

        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getPost().getId()
        );
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("id가 맞는 Comment가 존재하지 않습니다.")
        );

        comment.updateContent(dto.getContent());
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getPost().getId()
        );
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("id가 맞는 Comment가 존재하지 않습니다.")
        );

        commentRepository.deleteById(commentId);
    }
}
