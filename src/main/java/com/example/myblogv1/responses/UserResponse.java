package com.example.myblogv1.responses;

import com.example.myblogv1.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    Long  id;
    String   name;
    String  surname;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
    }


}
