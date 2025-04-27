package com.blog.blogApp.serviceimpl;

import com.blog.blogApp.entity.Category;
import com.blog.blogApp.entity.Post;
import com.blog.blogApp.entity.PostResponse;
import com.blog.blogApp.entity.User;
import com.blog.blogApp.exceptions.ResourceNotFoundException;
import com.blog.blogApp.repository.CategoryRepo;
import com.blog.blogApp.repository.PostRepo;
import com.blog.blogApp.repository.UserRepo;
import com.blog.blogApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Post createPost(Post post, Integer userId, Integer categoryId, Principal principal) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Sorry User Not Found", "Id", userId));
        if (!user.getEmail().equals(principal.getName())) {
            throw new ResourceNotFoundException("Sorry You Don't Have access For post", "", userId);
        }
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        post.setImageName("default.png");
        post.setPublishDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = this.postRepo.save(post);
        return createdPost;
    }

    @Override
    public Post updatePost(Post post, Integer postId, Principal principal) {
        Post foundPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        User user = foundPost.getUser();
        if (!user.getEmail().equals(principal.getName())) {
            throw new ResourceNotFoundException("Sorry You Don't Have access For post", "", postId);
        }
        foundPost.setCategory(post.getCategory());
        foundPost.setContent(post.getContent());
        foundPost.setImageName(post.getImageName());
        foundPost.setTitle(post.getTitle());
        foundPost.setPublishDate(post.getPublishDate());
        foundPost.setUser(post.getUser());
        this.postRepo.save(foundPost);
        return foundPost;
    }

    @Override
    public Post getPostById(Integer postId) {
        Post foundPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        return foundPost;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortby, String sortdir) {
        Sort sort = (sortdir.equalsIgnoreCase("asc")) ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagepost = this.postRepo.findAll(page);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pagepost.getContent());
        postResponse.setPageNumber(pageNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setLastPage(pagepost.isLast());
        postResponse.setTotalPages(pagepost.getTotalPages());
        postResponse.setTotalElements(pagepost.getNumberOfElements());
        return postResponse;

    }

    @Override
    public Post deletePost(Integer postId, Principal principal) {
        Post foundPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Not Found", "", postId));
        User user = foundPost.getUser();
        if (!user.getEmail().equals(principal.getName())) {
            throw new ResourceNotFoundException("Sorry You Don't Have access For post", "", postId);
        }
        this.postRepo.deleteById(postId);
        return foundPost;
    }

    @Override
    public List<Post> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        return posts;
    }

    @Override
    public List<Post> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        return posts;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        List<Post> posts = this.postRepo.searchPost(keyword);
        return posts;
    }


}
