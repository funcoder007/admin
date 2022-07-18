package com.code.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blog.entities.Category;
import com.code.blog.entities.User;

public interface CategoryRepo  extends JpaRepository<Category, Integer> {

}
