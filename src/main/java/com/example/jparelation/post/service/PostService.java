package com.example.jparelation.post.service;

import com.example.jparelation.post.dto.postDto.PostRequestDto;
import com.example.jparelation.post.dto.postDto.PostResponseDto;
import com.example.jparelation.post.entity.Post;
import com.example.jparelation.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto dto) {
        Post post = new Post(dto.getTitle());
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(
                savedPost.getId(),
                savedPost.getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> dtoList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getId(),
                    post.getTitle()
            );
            dtoList.add(postResponseDto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 Post가 존재하지 않습니다.")
        );

        return new PostResponseDto(
                post.getId(),
                post.getTitle()
        );
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 Post가 존재하지 않습니다.")
        );

        post.updateTitle(dto.getTitle());

        return new PostResponseDto(
                post.getId(),
                post.getTitle()
        );
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 Post가 존재하지 않습니다.")
        );

        postRepository.deleteById(postId);
    }
}
