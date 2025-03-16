package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDTO {
	
	private Long id;
	
	
	@NotNull(message = "Blog ID cannot be null")
    private Long blogId;

    @NotBlank(message = "Comment cannot be empty")
    @Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
    private String commentText;

    
	public CommentDTO() {
		super();
	}


	public CommentDTO(Long id, Long blogId, String commentText) {
		super();
		this.id = id;
		this.blogId = blogId;
		this.commentText = commentText;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
        return "CommentDTO { " +
                "id=" + id +
                ", blogId=" + blogId +
                ", commentText='" + commentText + '\'' +
                " }";
    }

}
