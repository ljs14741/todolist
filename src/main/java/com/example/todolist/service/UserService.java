package com.example.todolist.service;

import com.example.todolist.entity.User;

public interface UserService {

    User registerUser(String userName, String password);

    User findByUserName(String userName);

    void deleteUser(User user);

    User loginUser(String userName, String password);
}
