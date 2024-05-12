package com.example.todolist.controller;

import com.example.todolist.dto.UserDTO;
import com.example.todolist.entity.User;
import com.example.todolist.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            userService.registerUser(username, password);
            return ResponseEntity.ok("User registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is missing");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            User user = userService.findByUserName(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is missing");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            User user = userService.findByUserName(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                userService.deleteUser(user);
                return ResponseEntity.ok("User deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password are required");
        }
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUserName(username);
    }
}