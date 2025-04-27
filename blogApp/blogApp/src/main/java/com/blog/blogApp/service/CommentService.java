package com.blog.blogApp.service;

import com.blog.blogApp.entity.Comment;
import com.blog.blogApp.entity.Post;

public interface CommentService {
    Comment createComment(Comment comment, Integer postId);
    void deleteComment(Integer commentId);
}
