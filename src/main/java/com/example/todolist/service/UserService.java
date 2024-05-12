package com.example.todolist.service;

import com.example.todolist.entity.User;

public interface UserService {

    User registerUser(String userName, String password); // 회원가입

    User findByUserName(String userName); // 유저 조회

    void deleteUser(User user); // 회원탈퇴

//    User loginUser(String userName, String password);
}
