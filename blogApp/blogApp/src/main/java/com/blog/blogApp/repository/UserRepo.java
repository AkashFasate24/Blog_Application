package com.blog.blogApp.repository;

import com.blog.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(String username);
}
