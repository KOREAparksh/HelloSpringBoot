package com.example.demo.controller;

import com.example.demo.controller.dto.PostRequestDto;
import com.example.demo.controller.dto.PostResponseDto;
import com.example.demo.controller.dto.PostUpdateRequestDto;
import com.example.demo.controller.dto.ResultDto;
import com.example.demo.service.HomeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeApiController {
    @Autowired
    HomeApiService service;
    //CRUD

    //게시글 GET http://localhost:8080/api/post/(number)
    @GetMapping("/api/post/{id}")
    public ResultDto getPost(@PathVariable Integer id)
    {
        PostResponseDto rst;
        ResultDto rdto = new ResultDto();

        try {
            rst = service.getPost(id);
            rdto.setCode(HttpStatus.OK.value());
            rdto.setMessage("OK");
            rdto.setData(rst);
        }catch(Exception e){
            rdto.setCode(HttpStatus.FAILED_DEPENDENCY.value());
            rdto.setMessage(e.getMessage());
            rdto.setData(null);
        }
        return rdto;
    }

    @PostMapping("/api/post")
    public ResultDto addPost(@RequestBody PostRequestDto dto)
    {
        ResultDto rdto = new ResultDto();
        try {
            PostResponseDto rst = service.addPost(dto);
            rdto.setCode(HttpStatus.OK.value());
            rdto.setMessage("OK");
            rdto.setData(rst);
        }catch (Exception e){
            rdto.setCode(HttpStatus.FAILED_DEPENDENCY.value());
            rdto.setMessage(e.getMessage());
            rdto.setData(null);
        }
        return rdto;
    }

    @DeleteMapping("/api/post/{id}")
    public  ResultDto deletePost(@PathVariable Integer id){
        PostResponseDto rst;
        ResultDto rdto = new ResultDto();

        try {
            rst = service.deletePost(id);
            rdto.setCode(HttpStatus.OK.value());
            rdto.setMessage("OK");
            rdto.setData(rst);
        }catch(Exception e){
            rdto.setCode(HttpStatus.FAILED_DEPENDENCY.value());
            rdto.setMessage(e.getMessage());
            rdto.setData(null);
        }
        return rdto;
    }

    @PutMapping("/api/post")
    public ResultDto updatePost(@RequestBody PostUpdateRequestDto dto)
    {

        ResultDto rdto = new ResultDto();
        try {
            PostResponseDto rst = service.update(dto);
            rdto.setCode(HttpStatus.OK.value());
            rdto.setMessage("OK");
            rdto.setData(rst);
        }catch (Exception e){
            rdto.setCode(HttpStatus.FAILED_DEPENDENCY.value());
            rdto.setMessage(e.getMessage());
            rdto.setData(null);
        }
        return rdto;
    }
}
