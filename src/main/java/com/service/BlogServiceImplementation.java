package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
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
	public BlogDTO getBlogById(Long id) {
		BlogEntity blog = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

		return convertToDTO(blog);
	}

	@Override
	public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
		BlogEntity blog = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

		blog.setTitle(blogDTO.getTitle());
		blog.setContent(blogDTO.getContent());

		BlogEntity updatedBlog = blogRepository.save(blog);
		return convertToDTO(updatedBlog);
	}

	@Transactional
	@Override
	public void deleteBlog(Long id) {
		BlogEntity blog = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

		blogRepository.delete(blog);

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
