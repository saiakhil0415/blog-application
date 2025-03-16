package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id", nullable = false)
	private BlogEntity blog;

	@NotBlank(message = "Comment cannot be empty")
	@Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
	@Column(nullable = false, length = 200)
	private String commentText;

	public CommentEntity() {
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public BlogEntity getBlog() {
		return blog;
	}

	public void setBlog(BlogEntity blog) {
		this.blog = blog;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@Override
	public String toString() {
		return "CommentEntity { " + "commentId=" + commentId + ", blogId=" + (blog != null ? blog.getBlogId() : "null")
				+ ", commentText='" + commentText + '\'' + " }";
	}
}
