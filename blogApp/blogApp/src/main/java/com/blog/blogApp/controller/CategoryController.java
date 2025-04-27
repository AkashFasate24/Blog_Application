package com.blog.blogApp.controller;

import com.blog.blogApp.entity.Category;
import com.blog.blogApp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/{userId}")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, @PathVariable("userId") Integer userId) {
        Category createdCatgory = this.categoryService.createCategory(category, userId);
        return new ResponseEntity<>(createdCatgory, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Category>> allCategory() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Category> categoryById(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategoryByID(categoryId));
    }

    @PutMapping("/{categoryId}/{userId}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @PathVariable("categoryId") Integer categorId, @PathVariable("userId") Integer userId) {
        Category updatedCategory = this.categoryService.updateCategory(category, categorId,userId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/{userId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId, @PathVariable("userId") Integer userId) {
        this.categoryService.deleteCategory(categoryId,userId);
        return new ResponseEntity<>(Map.of("message", "Category Deleted Successfully !"), HttpStatus.OK);
    }

}
