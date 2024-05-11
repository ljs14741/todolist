package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder // sql에 값 넣는것
@AllArgsConstructor //생성자 자동 완성
@NoArgsConstructor //생성자 자동 완성
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

}