package com.code.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
