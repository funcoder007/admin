package com.code.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blog.entities.Category;
import com.code.blog.entities.Post;
import com.code.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	//custom finder methods
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
