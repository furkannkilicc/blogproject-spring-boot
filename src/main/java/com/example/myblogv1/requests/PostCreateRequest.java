package com.example.myblogv1.requests;


import lombok.Data;

@Data
public class PostCreateRequest {
    Long userId;
    String title;
    String text;



}
