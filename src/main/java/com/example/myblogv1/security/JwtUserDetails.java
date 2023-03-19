package com.example.myblogv1.security;

import com.example.myblogv1.entities.User;
import lombok.Data;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public  class JwtUserDetails implements UserDetails {
    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // TODO:ROLE USER YAPINCA WHITELABEL WHYs???
    public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(),authoritiesList);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).toList();
//    }

//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return getUsername();
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    private String username;
//    private  String password;
//    private List<GrantedAuthority> authorities;
//
//    public JwtUserDetails(User user) {
//        username= user.getUsername();
//        password=user.getPassword();
//        authorities = Arrays.stream(user.getRoles()
//                        .split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }



}
