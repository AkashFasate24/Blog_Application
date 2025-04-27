package com.blog.blogApp.repository;

import com.blog.blogApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
