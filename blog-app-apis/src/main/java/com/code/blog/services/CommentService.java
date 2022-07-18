package com.code.blog.services;

import com.code.blog.entities.Comment;
import com.code.blog.payloads.CommentDto;

public interface CommentService {
CommentDto createComment (CommentDto commentDto, Integer postId);
void deleteComment(Integer commentId);

}
