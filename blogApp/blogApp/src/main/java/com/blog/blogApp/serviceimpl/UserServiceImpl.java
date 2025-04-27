package com.blog.blogApp.serviceimpl;

import com.blog.blogApp.entity.User;
import com.blog.blogApp.exceptions.ResourceNotFoundException;
import com.blog.blogApp.repository.UserRepo;
import com.blog.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public User createUser(User user) {
        User savedUser = this.userRepo.save(user);
        return savedUser;
    }

    @Override
    public User updateUser(User user, Integer userId, Principal principal) {
        User foundUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        if (!foundUser.getEmail().equals(principal.getName())) {
            throw new ResourceNotFoundException("Sorry you Don't have access to update ", "", userId);
        }

        foundUser.setName(user.getName());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());
        foundUser.setAbout(user.getAbout());
        User updatedUser = this.userRepo.save(foundUser);
        return updatedUser;


    }

    @Override
    public User getUserByID(Integer userId) {
        User foundUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found", "", userId));
        return foundUser;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = this.userRepo.findAll();
        return users;
    }

    @Override
    public void deleteUser(Integer userId, Principal principal) {
        User deletedUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found", "", userId));
        if (!deletedUser.getEmail().equals(principal.getName())) {
            throw new ResourceNotFoundException("User Not available", "", userId);
        }
        this.userRepo.deleteById(userId);
    }
}
