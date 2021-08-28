package com.example.demo.service;

import com.example.demo.controller.dto.PostRequestDto;
import com.example.demo.controller.dto.PostResponseDto;
import com.example.demo.controller.dto.PostUpdateRequestDto;
import com.example.demo.domain.Post;
import com.example.demo.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HomeApiService {
    @Autowired
    HomeRepository repository;

    @Transactional
    public PostResponseDto getPost(int id) throws Exception {
//        Post post = repository.getById(id);
        PostResponseDto dto = new PostResponseDto();
        Post post = repository.findById(id)
                .orElseThrow(() -> new Exception("not found post"));

        dto.setId(post.getId());
        dto.setSubject(post.getSubject());
        dto.setContext(post.getContext());

        return dto;
    }

    @Transactional
    public PostResponseDto addPost(PostRequestDto dto) throws Exception {
        Post post = new Post();
        post.setSubject(dto.getSubject());
        post.setContext(dto.getContext());
        post = repository.save(post);

        if (post == null)
            throw new Exception("failed save");

        PostResponseDto rst  = new PostResponseDto();
        rst.setId(post.getId());
        rst.setSubject(post.getSubject());
        rst.setContext(post.getContext());
        return rst;
    }

    @Transactional
    public PostResponseDto deletePost(Integer id) throws Exception {
        Post post = repository.findById(id).orElseThrow(() -> new Exception("not found post"));
        repository.delete(post);

        PostResponseDto rst = new PostResponseDto();
        rst.setId(post.getId());
        rst.setSubject(post.getSubject());
        rst.setContext(post.getContext());
        return rst;
    }

    @Transactional
    public PostResponseDto update(PostUpdateRequestDto dto) throws Exception {
        Post post = repository.findById(dto.getId()).orElseThrow(() -> new Exception("not found post"));
        post.setId(dto.getId());
        post.setSubject(dto.getSubject());
        post.setContext(dto.getContext());
        post = repository.save(post);

//        if (post == null)
//            throw new Exception("update error : invalid dataset");

        PostResponseDto rst  = new PostResponseDto();
        rst.setId(post.getId());
        rst.setSubject(post.getSubject());
        rst.setContext(post.getContext());
        return rst;
    }
}
