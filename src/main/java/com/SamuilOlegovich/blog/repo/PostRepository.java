package com.SamuilOlegovich.blog.repo;

/*
CrudRepository - интерфейс имеет все необходимые методы для добавления удаления и т д из таблицы
*/
import com.SamuilOlegovich.blog.models.Post;
import org.springframework.data.repository.CrudRepository;



import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
