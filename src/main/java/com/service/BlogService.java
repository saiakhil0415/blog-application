package com.service;

import com.dto.BlogDTO;
import java.util.List;

public interface BlogService {
    BlogDTO createBlog(BlogDTO blogDTO);
    BlogDTO getBlogById(Long id);
    BlogDTO updateBlog(Long id, BlogDTO blogDTO);
    void deleteBlog(Long id);
    List<BlogDTO> getAllBlogs();
}
