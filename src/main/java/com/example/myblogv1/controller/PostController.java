package com.example.myblogv1.controller;

import com.example.myblogv1.requests.PostCreateRequest;
import com.example.myblogv1.requests.PostUpdateRequest;
import com.example.myblogv1.responses.PostResponse;
import com.example.myblogv1.services.PostService;
import org.springframework.web.bind.annotation.*;
import com.example.myblogv1.entities.Post;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePost(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostReq){
        return  postService.createOnePost(newPostReq);


    }
    @PutMapping("/{postId}")
    public  Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId,updatePost);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
         postService.deleteOnePostById(postId);
    }
}
