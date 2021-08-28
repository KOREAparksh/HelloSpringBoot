package com.example.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDto {
    Integer code;
    String message;
    Object data;
}
