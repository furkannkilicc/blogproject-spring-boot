package com.example.myblogv1.services;

import com.example.myblogv1.entities.Post;
import com.example.myblogv1.entities.User;
import com.example.myblogv1.repos.PostRepository;
import com.example.myblogv1.requests.PostCreateRequest;
import com.example.myblogv1.requests.PostUpdateRequest;
import com.example.myblogv1.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;

    }

//TODO: MAP() İLE collection oluşturup (list) döndürdük. örnek olarak bakılabilir. <>maptoPostResponse başka kullanımları da var.
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToPostResponse).toList();

//        List<Post> postList = postRepository.findAll();
//        List<PostResponse>postResponseList=postList.stream().map(PostResponse::new ).collect(Collectors.toList());
//        return  postResponseList;

   }
    private PostResponse mapToPostResponse(Post post) {
        PostResponse postRes = new  PostResponse(post);
        postRes.setText(post.getText());
        postRes.setTitle(post.getTitle());
        postRes.setUserId(post.getUser().getId());
        return postRes;
    }



    //TODO: BURAYI düzelt getUserId.describeConstantable
    public Post createOnePost(PostCreateRequest newPostReq) {
        User user = userService.getOneUserById(newPostReq.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setTitle(newPostReq.getTitle());
        toSave.setText(newPostReq.getText());
//        toSave.setId(newPostReq.getUserId());
        toSave.setUser(user);
        toSave.setPublished(new Date());
        return postRepository.save(toSave);

    }



    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdate) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setTitle(postUpdate.getTitle());
            toUpdate.setText(postUpdate.getText());
            postRepository.save(toUpdate);
            return  toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
         postRepository.deleteById(postId);
    }
}