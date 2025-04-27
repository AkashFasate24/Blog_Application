package com.blog.blogApp.controller;

import com.blog.blogApp.config.AppConstant;
import com.blog.blogApp.entity.Post;
import com.blog.blogApp.entity.PostResponse;
import com.blog.blogApp.repository.UserRepo;
import com.blog.blogApp.service.FileService;
import com.blog.blogApp.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    @Autowired
    private UserRepo userRepo;
    //post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<Post> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId,
            Principal principal
    ) throws IOException {
        Post post = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);

        post.setImageName(fileName);
        Post updatedPost = this.postService.updatePost(post, postId, principal);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/get/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


    @GetMapping("/get/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortby", defaultValue = AppConstant.SORT_BY, required = false) String sortby,
                                                   @RequestParam(value = "sortdir", defaultValue = AppConstant.SORT_DIR, required = false) String sortdir) {
        PostResponse posts = this.postService.getAllPost(pageNumber, pageSize, sortby, sortdir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/get/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId) {
        Post post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId, Principal principal) {

        Post createdPost = this.postService.createPost(post, userId, categoryId, principal);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/get/user/{userId}/posts")
    public ResponseEntity<List<Post>> getPostByUser(@PathVariable("userId") Integer userId) {
        List<Post> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/get/category/{categoryId}/posts")
    public ResponseEntity<List<Post>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<Post> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable("postId") Integer postId, Principal principal) {
        Post deletedPost = this.postService.deletePost(postId, principal);
        return new ResponseEntity<>(deletedPost, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("postId") Integer postId, Principal principal) {
        Post updatedPost = this.postService.updatePost(post, postId, principal);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/get/posts/search/{keyword}")
    public ResponseEntity<List<Post>> searchPost(@PathVariable("keyword") String keyword) {
        List<Post> posts = this.postService.searchPost(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
