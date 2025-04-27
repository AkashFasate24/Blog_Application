package com.blog.blogApp.controller;

import com.blog.blogApp.entity.User;
import com.blog.blogApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private User createUser;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createUser = this.userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    //Put - Update api
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Integer userId, Principal principal) {
        User updatedUser = this.userService.updateUser(user, userId,principal);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId,Principal principal) {
        this.userService.deleteUser(userId,principal);
        return new ResponseEntity<>(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/byid/{userId}")
    public ResponseEntity<User> getUserByID(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userService.getUserByID(userId));
    }

}
