package com.example.myblogv1.services;

import com.example.myblogv1.entities.User;
import com.example.myblogv1.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getOneUserById(Long userId){
        return  userRepository.findById(userId).orElse(null);
    }
//TODO:PASSWORD ENCODER BEAN EKLENDİ SECURITYCONFIG DE TANIMLANDI BCRYPT
    public User AddUser(User newUser) {
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return  userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setName(newUser.getName());
            foundUser.setSurname(newUser.getSurname());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setRoles(newUser.getRoles());
            userRepository.save(foundUser);
            return  foundUser;

        }else
            return  null;
    }
    public User getOneUserByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
