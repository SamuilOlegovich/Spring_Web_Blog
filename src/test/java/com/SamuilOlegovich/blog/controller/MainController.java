package com.SamuilOlegovich.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



@Controller
public class MainController {

    @GetMapping("/") // если у нас /- то обрабатывается главная страница (слеш)
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
}
