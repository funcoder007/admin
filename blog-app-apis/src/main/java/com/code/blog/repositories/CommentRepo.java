package com.code.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
