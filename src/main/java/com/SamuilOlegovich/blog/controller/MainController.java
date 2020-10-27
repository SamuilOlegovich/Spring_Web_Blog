package com.SamuilOlegovich.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



@Controller     // анотация к классу кто он и т д
public class MainController {

    // анотаця к методу что он будет отслеживать
    @GetMapping("/") // если у нас /- то обрабатывается главная страница (слеш)
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    // анотаця к методу что он будет отслеживать
    @GetMapping("/about") // если у нас /about - то обрабатывается страница about
    public String about(Model model) {
        model.addAttribute("title", "Страница про нас");
        return "about";
    }
}
