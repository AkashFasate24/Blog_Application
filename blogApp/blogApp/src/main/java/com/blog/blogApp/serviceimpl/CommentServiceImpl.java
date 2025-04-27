package com.blog.blogApp.serviceimpl;

import com.blog.blogApp.entity.Comment;
import com.blog.blogApp.entity.Post;
import com.blog.blogApp.exceptions.ResourceNotFoundException;
import com.blog.blogApp.repository.CommentRepo;
import com.blog.blogApp.repository.PostRepo;
import com.blog.blogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Comment createComment(Comment comment, Integer postId) {
        Post foundPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Doesn't Found", "", postId));
        comment.setPost(foundPost);
        Comment saveComment = this.commentRepo.save(comment);
        return saveComment;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment foundComment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Commet Doesnt Exist", "", commentId));
        this.commentRepo.delete(foundComment);
    }
}
