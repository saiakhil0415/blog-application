package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CommentDTO;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exceptions.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service
public class CommentServiceImplementation implements CommentService {

	private final CommentRepository commentRepository;
	private final BlogRepository blogRepository;

	public CommentServiceImplementation(CommentRepository commentRepository, BlogRepository blogRepository) {
		this.commentRepository = commentRepository;
		this.blogRepository = blogRepository;
	}

	@Transactional
	@Override
	public CommentDTO postComment(CommentDTO commentDTO) {
		BlogEntity blog = blogRepository.findById(commentDTO.getBlogId())
				.orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + commentDTO.getBlogId()));

		CommentEntity comment = convertToEntity(commentDTO, blog);

		CommentEntity savedComment = commentRepository.save(comment);
		return convertToDTO(savedComment);
	}

	@Override
	public List<CommentDTO> getCommentsByBlogId(Long blogId) {
		 blogRepository.findById(blogId)
         .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + blogId));

		List<CommentEntity> comments = commentRepository.findByBlogId(blogId);
		return comments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Conversion Methods
	private CommentDTO convertToDTO(CommentEntity commentEntity) {
		return new CommentDTO(commentEntity.getId(), commentEntity.getBlog().getId(), commentEntity.getCommentText());
	}

	private CommentEntity convertToEntity(CommentDTO commentDTO, BlogEntity blog) {
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setCommentText(commentDTO.getCommentText());
		commentEntity.setBlog(blog);
		return commentEntity;
	}
}
