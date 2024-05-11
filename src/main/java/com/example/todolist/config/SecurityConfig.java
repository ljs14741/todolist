package com.example.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//
//// Security 6.1.x 부터 메소드 변경
@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequest -> // 인증된 사용자
//                        authorizeRequest
//                                .requestMatchers(
//                                        AntPathRequestMatcher.antMatcher("/auth/**")
//                                ).authenticated()
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login").permitAll() // 접근허용
//                )
//                .logout(logout ->
//                        logout
//                                .logoutSuccessUrl("/login?logout").permitAll() // login?logout redirection 및 인증없이 접근 ㅌ
//                )
//                .csrf(csrf ->
//                        csrf.disable()
//                );
//
//        return http.build();
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}