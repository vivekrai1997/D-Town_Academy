package com.comeandlearn.services;

import java.util.List;

import com.comeandlearn.entity.Comment;

public interface CommentService {
	String addComment(Comment comment);
	List<Comment> getComments();

}
