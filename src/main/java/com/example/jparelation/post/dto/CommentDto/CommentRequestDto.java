package com.example.jparelation.post.dto.CommentDto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String content;
    private Long postId;
}
