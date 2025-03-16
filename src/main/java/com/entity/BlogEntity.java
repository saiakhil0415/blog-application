package com.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "blogs")
public class BlogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blogId;

	@NotBlank(message = "Title cannot be empty")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	@Column(nullable = false, length = 100)
	private String blogTitle;

	@NotBlank(message = "Content cannot be empty")
	@Size(min = 3, max = 200, message = "Content must be between 3 and 200 characters")
	@Column(nullable = false, length = 200)
	private String blogContent;

	@CreationTimestamp 
	@Column(nullable = false, updatable = false)
	private LocalDate blogCreatedDate;

	public BlogEntity() {
	}

	public Long getId() {
		return blogId;
	}

	public void setId(Long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return blogTitle;
	}

	public void setTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getContent() {
		return blogContent;
	}

	public void setContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public LocalDate getCreatedAt() {
		return blogCreatedDate;
	}

	public void setCreatedAt(LocalDate blogCreatedDate) {
		this.blogCreatedDate = blogCreatedDate;
	}

	@Override
	public String toString() {
		return "BlogEntity { " + "blogId=" + blogId + ", blogTitle='" + blogTitle + '\'' + ", blogContent='" + blogContent + '\''
				+ ", blogCreatedDate=" + blogCreatedDate + " }";
	}

}
