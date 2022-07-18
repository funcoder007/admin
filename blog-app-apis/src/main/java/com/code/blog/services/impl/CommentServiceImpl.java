package com.code.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blog.entities.Comment;
import com.code.blog.entities.Post;
import com.code.blog.entities.User;
import com.code.blog.exceptions.ResourceNotFoundException;
import com.code.blog.payloads.CategoryDto;
import com.code.blog.payloads.CommentDto;
import com.code.blog.repositories.CommentRepo;
import com.code.blog.repositories.PostRepo;
import com.code.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId)); 
		
	 Comment comment = this.modelMapper.map(commentDto, Comment.class);
	 comment.setPost(post);
	 Comment savedComment = this.commentRepo.save(comment);
	return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("comment", "CommentId", commentId)); 
		this.commentRepo.delete(com);
	}

}
