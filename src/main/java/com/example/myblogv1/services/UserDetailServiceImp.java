package com.example.myblogv1.services;

import com.example.myblogv1.entities.User;
import com.example.myblogv1.repos.UserRepository;
import com.example.myblogv1.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//service
@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final   UserRepository userRepository;
    public UserDetailServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username);
            return  JwtUserDetails.create(user);


    }
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }

//DAN VEGA
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserRepository.
//        return null;
//    }




//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return  userRepository.findByUsername(username)
//                .map(i->new JwtUserDetails()).orElseThrow(()->new UsernameNotFoundException("kullanıcı adı bulunamadı hatas USERDETAILS SERVICE"));
//    }




 //    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findbyName(username);
//        return JwtUserDetails.create(user);
//
//    }
//    public UserDetails loadUserById(Long id){
//        User user = userRepository.findById(id).get();
//        return JwtUserDetails.create(user);
//    }
}
//DB DEN USER I GETIRIYORUZ KARSILASTIRIYORUZ
