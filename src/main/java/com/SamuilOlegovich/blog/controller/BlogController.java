package com.SamuilOlegovich.blog.controller;


import com.SamuilOlegovich.blog.models.Post;
import com.SamuilOlegovich.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class BlogController {

    @Autowired                              // необходимо для создания переменной которая ссылается на репозиторий
    private PostRepository postRepository;

    @GetMapping("/blog-main")
    public String blogMain(Model model) {
        // в специальный массив вытаскиваем все значения из базы
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "blog-main";
    }
}
