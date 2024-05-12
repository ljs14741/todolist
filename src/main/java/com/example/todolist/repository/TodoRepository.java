package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserName(String username); // 전체 목록 todoList
    Todo findTopByUserNameOrderByCreateDtDesc(String username); // 최근 목록 todoList
}