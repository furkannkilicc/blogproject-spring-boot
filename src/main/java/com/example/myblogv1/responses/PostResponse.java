package com.example.myblogv1.responses;

import com.example.myblogv1.entities.Post;
import lombok.Data;

@Data
public class PostResponse {



    String title;
    String text;
    Long userId;
    public PostResponse(Post entity) {
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.userId =entity.getUser().getId();
        //entity içerisinde başka tanımlama varsa mapleme

    }
}
