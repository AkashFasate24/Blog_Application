package com.blog.blogApp.service;

import com.blog.blogApp.entity.Category;
import com.blog.blogApp.entity.Post;
import com.blog.blogApp.entity.PostResponse;
import com.blog.blogApp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.Principal;
import java.util.List;

public interface PostService {
    Post createPost(Post post, Integer postId, Integer categoryId, Principal principal);

    Post updatePost(Post post, Integer postId,Principal principal);

    Post getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortby, String sortdir);

    Post deletePost(Integer Post,Principal principal);

    List<Post> getPostByUser(Integer userId);

    List<Post> getPostByCategory(Integer categoryId);

    List<Post> searchPost(String keyword);

}
