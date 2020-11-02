package com.SamuilOlegovich.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.SamuilOlegovich.blog.repo.PostRepository;
import org.springframework.stereotype.Controller;
import com.SamuilOlegovich.blog.models.Post;
import org.springframework.ui.Model;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.Optional;


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
 для прописания динамического параметра - {id} - @PathVariable(value = "id")
*/
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id, Model model) {
        if (postRepository.existsById(id)) {

            Optional<Post> post = postRepository.findById(id);
            ArrayList<Post> arrayPostList = new ArrayList<>();
            post.ifPresent(arrayPostList::add);
            model.addAttribute("post", arrayPostList);
            return "blog-add";
        }
        return "redirect:/blog-main";
    }



    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model) {
        if (postRepository.existsById(id)) {
            Optional<Post> post = postRepository.findById(id);
            ArrayList<Post> arrayPostList = new ArrayList<>();
            post.ifPresent(arrayPostList::add);
            model.addAttribute("post", arrayPostList);
            return "blog-edit";
        }
        return "redirect:/blog";
    }


/*
     @PostMapping("/blog/add") пост потому что мы тут уже отслеживаем и принимаем данные с указаной страницы
     @RequestPart String title - сообщает о том что мы получаем данные, какого типа и из какого поля
*/
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestPart String title, @RequestPart String anons,
                              @RequestPart String full_text, Model model) {

        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        // redirect:/ - для переадресации на другую страницу
        return "redirect:/blog";
    }


    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") Long id, @RequestPart String title,
                                 @RequestPart String anons, @RequestPart String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setFull_text(full_text);
        post.setTitle(title);
        post.setAnons(anons);
        postRepository.save(post);
        return "redirect:/blog";
    }



    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
