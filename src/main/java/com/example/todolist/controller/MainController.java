package com.example.todolist.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    // 메인화면 호출
    @RequestMapping("/login")
    public String user() {
        return "user";
    }

    // 로그인 후 todolist 화면 호출
    @RequestMapping("/todo")
    public String todo() {
        return "todo";
    }

}
