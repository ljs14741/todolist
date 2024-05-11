package com.example.todolist.controller;

import com.example.todolist.dto.UserDTO;
import com.example.todolist.entity.User;
import com.example.todolist.service.UserService;
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
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO.getUserName(), userDTO.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        User user = userService.findByUserName(userDTO.getUserName());
        if (user != null && passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        if (user != null) {
            userService.deleteUser(user);
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUserName(username);
    }
}