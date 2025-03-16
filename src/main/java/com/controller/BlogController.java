package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BlogDTO;
import com.dto.CommentDTO;
import com.service.BlogService;
import com.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	private final BlogService blogService;
	private final CommentService commentService;

	public BlogController(BlogService blogService, CommentService commentService) {
		this.blogService = blogService;
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
		BlogDTO createdBlog = blogService.createBlog(blogDTO);
//		return ResponseEntity.status(201).body(createdBlog);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
		
	}

	@GetMapping("/{id}")
//	public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
//		BlogDTO blog = blogService.getBlogById(id);
//		return ResponseEntity.ok(blog);
//	}
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        try {
            BlogDTO blog = blogService.getBlogById(id);
            return ResponseEntity.ok(blog);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

	@PutMapping("/{id}")
//	public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDTO) {
//		BlogDTO updatedBlog = blogService.updateBlog(id, blogDTO);
//		return ResponseEntity.ok(updatedBlog);
//	}
	public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDTO) {
        try {
            BlogDTO updatedBlog = blogService.updateBlog(id, blogDTO);
            return ResponseEntity.ok(updatedBlog);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
//		blogService.deleteBlog(id);
//		return ResponseEntity.noContent().build();
//	}
	public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        try {
            blogService.deleteBlog(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

	@GetMapping
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<BlogDTO> blogs = blogService.getAllBlogs();
		return ResponseEntity.ok(blogs);
	}

	@PostMapping("/comment")
//	public ResponseEntity<CommentDTO> postComment(@Valid @RequestBody CommentDTO commentDTO) {
//		CommentDTO savedComment = commentService.postComment(commentDTO);
//		return ResponseEntity.ok(savedComment);
//	}
	public ResponseEntity<?> postComment(@Valid @RequestBody CommentDTO commentDTO) {
        try {
            CommentDTO savedComment = commentService.postComment(commentDTO);
            return ResponseEntity.ok(savedComment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found with ID: " + commentDTO.getBlogId());
        }
    }

	@GetMapping("/comment")
//	public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@RequestParam Long blogId) {
//		List<CommentDTO> comments = commentService.getCommentsByBlogId(blogId);
//		return ResponseEntity.ok(comments);
//	}
	public ResponseEntity<?> getCommentsByBlogId(@RequestParam Long blogId) {
        try {
            List<CommentDTO> comments = commentService.getCommentsByBlogId(blogId);
            return ResponseEntity.ok(comments);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog not found with ID: " + blogId);
        }
    }

}
