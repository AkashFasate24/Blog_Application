package com.blog.blogApp.repository;

import com.blog.blogApp.entity.Category;
import com.blog.blogApp.entity.Post;
import com.blog.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
    List<Post> searchPost(@Param("key") String keyword);
}
