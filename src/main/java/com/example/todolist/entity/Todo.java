package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder // sql에 값 넣는것
@AllArgsConstructor //생성자 자동 완성
@NoArgsConstructor //생성자 자동 완성
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY) // User 외래키
    @JoinColumn(name = "user_name")
    private String userName;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "create_dt")
    private String createDt;

    @Column(name = "update_dt")
    private String updateDt;

}
