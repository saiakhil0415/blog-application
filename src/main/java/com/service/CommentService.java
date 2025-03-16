package com.service;

import com.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO postComment(CommentDTO commentDTO);
    List<CommentDTO> getCommentsByBlogId(Long blogId);
}
