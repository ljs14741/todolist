package com.example.todolist.service.Impl;

import com.example.todolist.entity.User;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    public User registerUser(String userName, String password) {
        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 유저 생성
        User user = new User();
        user.setUserName(userName);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    // 유저 조회
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    // 회원탈퇴
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


//    @Override
//    public User loginUser(String userName, String password) {
//        User user = userRepository.findByUserName(userName);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }



}