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
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String loggedInUsername = loggedInUser.getUserName();
        List<TodoDTO> todos = todoService.getTodosByUserName(loggedInUsername);
        TodoDTO recentTodo = todoService.getRecentTodoByUsername(loggedInUsername);
        model.addAttribute("recentTodo", recentTodo);
        model.addAttribute("todos", todos);
        return "todoList";
    }

    // todoList 할일 추가
    @PostMapping("/todos/add")
    public String addTodo(@RequestParam String title, HttpSession session) {
        try {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("할 일을 입력하세요");
            }

            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                throw new IllegalArgumentException("로그인을 먼저하십시오.");
            }

            String userName = loggedInUser.getUserName();

            TodoDTO newTodo = TodoDTO.builder()
                    .userName(userName)
                    .title(title)
                    .build();
            todoService.addTodo(newTodo);
            return "redirect:/todos";
        } catch (IllegalArgumentException e) {
            log.error("todoList를 추가하다가 오류가 발생하였습니다.: {}", e.getMessage());
            return "redirect:/todos?error=" + e.getMessage();
        }
    }

    // todoList 상태 변경
    @PostMapping("/todos/{id}/status")
    public String updateTodoStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            todoService.updateTodoStatus(id, status);
            return "redirect:/todos";
        } catch (IllegalArgumentException e) {
            log.error("상태 변경하다가 오류가 발새하였습니다.: {}", e.getMessage());
            return "redirect:/todos?error=" + e.getMessage();
        }
    }
}