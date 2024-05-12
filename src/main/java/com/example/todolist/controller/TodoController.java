package com.example.todolist.controller;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.User;
import com.example.todolist.service.TodoService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private HttpSession session;

    // todoList 목록 조회
    @GetMapping("/todos")
    public String getTodos(Model model) {
        TodoDTO recentTodo = todoService.getRecentTodo();
        List<TodoDTO> todos = todoService.getAllTodos();
        model.addAttribute("recentTodo", recentTodo);
        model.addAttribute("todos", todos);
        return "todoList"; // Thymeleaf 템플릿 이름 반환
    }

    // todoList 할일 추가
    @PostMapping("/todos/add")
    public String addTodo(@RequestParam String title, HttpSession session) {
        String userName = ((User) session.getAttribute("loggedInUser")).getUserName();

        TodoDTO newTodo = TodoDTO.builder()
                .userName(userName)
                .title(title)
                .build();
        todoService.addTodo(newTodo);
        return "redirect:/todos";
    }

    // todoList 상태 변경
    @PostMapping("/todos/{id}/status")
    public String updateTodoStatus(@PathVariable Long id, @RequestParam String status) {
        todoService.updateTodoStatus(id, status);
        return "redirect:/todos";
    }
}