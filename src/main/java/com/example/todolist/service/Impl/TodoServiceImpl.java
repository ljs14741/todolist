package com.example.todolist.service.Impl;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;


    //    todoList 최근 목록 조회
    @Override
    public TodoDTO getRecentTodoByUsername(String username) {
        Todo recentTodo = todoRepository.findTopByUserNameOrderByCreateDtDesc(username);
        return convertToDTO(recentTodo);
    }

    //    todoList 전체 목록 조회
    @Override
    public List<TodoDTO> getTodosByUserName(String username) {
        List<Todo> todos = todoRepository.findByUserName(username);
        return todos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //    todoList 할일 추가
    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        Todo todo = Todo.builder()
                .userName(todoDTO.getUserName())
                .title(todoDTO.getTitle())
                .status("해야할 일")
                .createDt(LocalDateTime.now().toString())
                .build();
        Todo savedTodo = todoRepository.save(todo);
        return convertToDTO(savedTodo);
    }

    //    todoList 상태 변경
    @Override
    public TodoDTO updateTodoStatus(Long id, String status) {
        Todo todo = todoRepository.getById(id);
        String currentStatus = todo.getStatus();

        // 변경하려는 상태가 "대기"이고, 현재 상태가 "진행 중"이 아닌 경우에만 alert 창을 띄우고 변경을 중단
        if (status.equals("대기") && !currentStatus.equals("진행 중")) {
            throw new IllegalArgumentException("진행 중 상태에서만 대기 상태로 변경할 수 있습니다.");
        }

        // 그 외의 경우에는 상태를 변경하고 저장
        todo.setStatus(status);
        todo.setUpdateDt(LocalDateTime.now().toString());
        Todo updatedTodo = todoRepository.save(todo);
        return convertToDTO(updatedTodo);
    }

    // Entity -> DTO 변환
    private TodoDTO convertToDTO(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .status(todo.getStatus())
                .createDt(todo.getCreateDt())
                .build();
    }
}