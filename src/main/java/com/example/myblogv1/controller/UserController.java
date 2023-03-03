package com.example.myblogv1.controller;

import com.example.myblogv1.entities.User;
import com.example.myblogv1.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

@GetMapping
public List<User>  gelAllUsers(){
        return  userService.getAllUsers();
}
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUserById(userId);
    }
    @PostMapping
        public User createOneUser(@RequestBody User newUser){
        return userService.AddUser(newUser);

        }
        @DeleteMapping("/{userId}")
        public  void deleteOneUser(@PathVariable Long userId){
         userService.deleteUser(userId);
        }
        @PutMapping("/{userId}")
    public ResponseEntity<Void> updateOneUser(@PathVariable Long userId , @RequestBody User newUser){
        User user = userService.updateOneUser(userId,newUser);
        if(user!=null)
            return  new ResponseEntity<>(HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


}
