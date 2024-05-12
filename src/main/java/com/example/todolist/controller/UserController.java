package com.example.todolist.controller;

import com.example.todolist.dto.UserDTO;
import com.example.todolist.entity.User;
import com.example.todolist.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Username, password를 입력하세요.");
            }

            userService.registerUser(username, password);
            return ResponseEntity.ok("회원가입이 완료되었습니다. 뒤로가기를 누르신 후 로그인을 하세요.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입을 진행하다 오류가 발생하였습니다. 관리자에게 문의하세요.");
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("username, password를 입력하세요.");
            }

            User user = userService.findByUserName(username);
            if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("username, password가 틀렸습니다.");
            }

            session.setAttribute("loggedInUser", user);
            return ResponseEntity.ok("로그인이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인을 진행하다 오류가 발생하였습니다. 관리자에게 문의하세요.");
        }
    }

    // 회원탈퇴
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Username, password를 입력하세요.");
            }

            User user = userService.findByUserName(username);
            if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("username, password가 틀렸습니다.");
            }

            userService.deleteUser(user);
            return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴를 진행하다 오류가 발생하였습니다. 관리자에게 문의하세요.");
        }
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        try {
            return userService.findByUserName(username);
        } catch (Exception e) {
            log.error("유저 조회 실패: {}", e.getMessage());
            return null;
        }
    }
}