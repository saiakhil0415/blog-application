package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
import com.exceptions.BlogNotFoundException;
import com.repository.BlogRepository;

@Service
public class BlogServiceImplementation implements BlogService {

	private final BlogRepository blogRepository;

	public BlogServiceImplementation(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public BlogDTO createBlog(BlogDTO blogDTO) {
		BlogEntity blogEntity = convertToEntity(blogDTO);
		BlogEntity savedBlog = blogRepository.save(blogEntity);

		return convertToDTO(savedBlog);
	}

	@Override
	public BlogDTO getBlogById(Long blogId) {
	    BlogEntity blogEntity = blogRepository.findById(blogId)
	            .orElseThrow(() -> new BlogNotFoundException("Blog not found with ID: " + blogId));
	    return convertToDTO(blogEntity);
	}


	@Override
	public BlogDTO updateBlog(Long blogId, BlogDTO blogDTO) {
		BlogEntity blogEntity = blogRepository.findById(blogId)
				.orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + blogId));

		blogEntity.setTitle(blogDTO.getTitle());
		blogEntity.setContent(blogDTO.getContent());

		BlogEntity updatedBlog = blogRepository.save(blogEntity);
		return convertToDTO(updatedBlog);
	}

	@Transactional
	@Override
	public void deleteBlog(Long blogId) {
		BlogEntity blogEntity = blogRepository.findById(blogId)
				.orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + blogId));

		blogRepository.delete(blogEntity);

	}

	@Override
	public List<BlogDTO> getAllBlogs() {
		return blogRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Conversion Methods
	private BlogDTO convertToDTO(BlogEntity blogEntity) {
		return new BlogDTO(blogEntity.getId(), blogEntity.getTitle(), blogEntity.getContent());
	}

	private BlogEntity convertToEntity(BlogDTO blogDTO) {
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setTitle(blogDTO.getTitle());
		blogEntity.setContent(blogDTO.getContent());
		return blogEntity;
	}

}
