package com.blog.blogApp.controller;

import com.blog.blogApp.entity.ApiResponse;
import com.blog.blogApp.entity.Comment;
import com.blog.blogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Integer postId) {
        Comment createdComment = this.commentService.createComment(comment, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponse> deletecomment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Deleted Comment SuccessFully ! ", true), HttpStatus.OK);
    }

}
