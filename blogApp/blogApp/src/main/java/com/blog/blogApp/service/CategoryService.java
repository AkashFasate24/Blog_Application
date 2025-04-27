package com.blog.blogApp.service;

import com.blog.blogApp.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category, Integer userId);

    Category updateCategory(Category category, Integer categoryID, Integer useId);

    Category getCategoryByID(Integer categoryID);

    List<Category> getAllCategories();

    void deleteCategory(Integer categoryId, Integer userId);
}
