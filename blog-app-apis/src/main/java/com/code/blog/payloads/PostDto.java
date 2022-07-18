package com.code.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.code.blog.entities.Category;
import com.code.blog.entities.Comment;
import com.code.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	private String title;
	private String content;
	
	
	private String imageName;
	private Date addedDate;
	
	
	
	private CategoryDto category;
	
	private UserDto user;
  private Set<CommentDto> comments = new HashSet<>();
}
