package com.SamuilOlegovich.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.GetMapping;
import com.SamuilOlegovich.blog.repo.PostRepository;
import org.springframework.stereotype.Controller;
import com.SamuilOlegovich.blog.models.Post;
import org.springframework.ui.Model;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;


@Controller
public class BlogController {
/*
    необходимо для создания переменной
    которая ссылается на репозиторий
*/
    @Autowired
    private PostRepository postRepository;



    @GetMapping("/blog")
    public String blogMain(Model model) {
        // в специальный массив вытаскиваем все значения из базы
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

/*
    @GetMapping("/blog/add") потому что мы получаем что-то от сервера,
    в частности переход на новую страницу
*/
    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }
/*
     @PostMapping("/blog/add") пост потому что мы тут уже отслеживаем и принимаем данные с указаной страницы
     @RequestPart String title - сообщает о том что мы получаем данные, какого типа и из какого поля
*/
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestPart String title, @RequestPart String anons,
                              @RequestPart String full_text, Model model) {
//    @GetMapping("/blog/add")
//    public String blogPostAdd(Model model) {

        //Post post = new Post(title, anons, full_text);
//        postRepository.save(post);
        // redirect:/ - для переадресации на другую страницу
        return "blog-add";
    }

}
