package com.blog.blogApp.service;

import com.blog.blogApp.entity.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, Integer userId, Principal principal);
    User getUserByID(Integer userId);
    List<User> getAllUser();
    void deleteUser(Integer userId,Principal principal);

}

