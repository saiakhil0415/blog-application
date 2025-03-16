package com.service;

import com.dto.BlogDTO;
import java.util.List;

public interface BlogService {
    BlogDTO createBlog(BlogDTO blogDTO);
    BlogDTO getBlogById(Long blogId);
    BlogDTO updateBlog(Long blogId, BlogDTO blogDTO);
    void deleteBlog(Long blogId);
    List<BlogDTO> getAllBlogs();
}
