package com.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/login")
    public String authController() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminController() {
        return "admin";
    }
}
