package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BlogDTO {
	private Long blogId;

	@NotBlank(message = "Title cannot be empty")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	private String blogTitle;

	@NotBlank(message = "Content cannot be empty")
	@Size(min = 3, max = 200, message = "Content must be between 3 and 200 characters")
	private String blogContent;

	public BlogDTO() {
		super();
	}

	public BlogDTO(Long blogId, String blogTitle, String blogContent) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	@Override
	public String toString() {
		return "BlogDTO { " + "blogId=" + blogId + ", blogTitle='" + blogTitle + '\'' + ", blogContent='" + blogContent
				+ '\'' + " }";
	}

}
