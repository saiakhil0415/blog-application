package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDTO {

	private Long commentId;

	@NotNull(message = "Blog ID cannot be null")
	private Long blogId;

	@NotBlank(message = "Comment cannot be empty")
	@Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
	private String commentText;

	public CommentDTO() {
		super();
	}

	public CommentDTO(Long commentId, Long blogId, String commentText) {
		super();
		this.commentId = commentId;
		this.blogId = blogId;
		this.commentText = commentText;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@Override
	public String toString() {
		return "CommentDTO { " + "commentId=" + commentId + ", blogId=" + blogId + ", commentText='" + commentText
				+ '\'' + " }";
	}

}
