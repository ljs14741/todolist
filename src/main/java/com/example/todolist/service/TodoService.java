package com.example.todolist.service;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.Todo;

import java.util.List;

public interface TodoService {
    TodoDTO getRecentTodo(); // 최근 todoList 조회
    List<TodoDTO> getAllTodos(); // 전체 todoList 목록 조회
    TodoDTO addTodo(TodoDTO todoDTO); // todoList 할일 추가
    TodoDTO updateTodoStatus(Long id, String status); // todoList 상태 변경
}