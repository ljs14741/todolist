package com.example.todolist.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long id;
    private String userName;
    private String title;
    private String status;
    private String createDt;
}