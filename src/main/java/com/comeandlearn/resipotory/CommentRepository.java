package com.comeandlearn.resipotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
