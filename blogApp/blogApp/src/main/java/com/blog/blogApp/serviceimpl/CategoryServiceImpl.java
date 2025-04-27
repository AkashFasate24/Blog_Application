package com.blog.blogApp.serviceimpl;

import com.blog.blogApp.entity.Category;
import com.blog.blogApp.entity.User;
import com.blog.blogApp.exceptions.ResourceNotFoundException;
import com.blog.blogApp.repository.CategoryRepo;
import com.blog.blogApp.repository.UserRepo;
import com.blog.blogApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Category createCategory(Category category, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            Category createdCategory = this.categoryRepo.save(category);
            return createdCategory;
        } else {
            throw new ResourceNotFoundException("", "Sorry You Don't have access To create Category", userId);
        }

    }

    @Override
    public Category updateCategory(Category category, Integer categoryID, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            Category foundCategory = this.categoryRepo.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryID));
            foundCategory.setCategoryName(category.getCategoryName());
            foundCategory.setCatDescription(category.getCatDescription());
            this.categoryRepo.save(foundCategory);
            return foundCategory;
        } else {
            throw new ResourceNotFoundException("", "Sorry You don't have access To Update Category", userId);
        }

    }

    @Override
    public Category getCategoryByID(Integer categoryID) {
        Category foundCategory = this.categoryRepo.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryID));
        return foundCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategory = this.categoryRepo.findAll();
        return allCategory;
    }

    @Override
    public void deleteCategory(Integer categoryId, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            Category foundCategory = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
            this.categoryRepo.deleteById(categoryId);
        } else {
            throw new ResourceNotFoundException("", "Sorry you don't have Access to delete Category", userId);
        }
    }
}
