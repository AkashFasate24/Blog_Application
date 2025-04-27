package com.blog.blogApp.repository;

import com.blog.blogApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
