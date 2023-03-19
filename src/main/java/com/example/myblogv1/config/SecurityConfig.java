package com.example.myblogv1.config;

import com.example.myblogv1.security.JwtAuthenticationEntryPoint;
import com.example.myblogv1.security.JwtAuthenticationFilter;
import com.example.myblogv1.services.UserDetailServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
//TODO:auth provider passencoder security filter userdetails** config **de

    private  final JwtAuthenticationEntryPoint handler;
    private final UserDetailServiceImp userDetailsService;

    public SecurityConfig(JwtAuthenticationEntryPoint handler, UserDetailServiceImp userDetailsService) {
        this.handler = handler;
        this.userDetailsService = userDetailsService;
    }
//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return  new org.springframework.web.filter.CorsFilter(source)
//    }
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("OPTIONS");
      config.addAllowedMethod("HEAD");
      config.addAllowedMethod("GET");
      config.addAllowedMethod("PUT");
      config.addAllowedMethod("POST");
      config.addAllowedMethod("DELETE");
      config.addAllowedMethod("PATCH");
     source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailServiceImp();
//    }
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }


@Bean
public  PasswordEncoder passwordEncoder(){
   return new BCryptPasswordEncoder();

}


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                  http.cors().and()
                        .csrf()
                        .disable()
                .exceptionHandling().authenticationEntryPoint(handler).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                          .authorizeHttpRequests()
                        .requestMatchers("/auth/**")
                         .permitAll().requestMatchers("/posts/**").permitAll().requestMatchers("/users/**").permitAll()
                          .anyRequest().authenticated();

//                http.addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
                return  http.build();
                            }
}
