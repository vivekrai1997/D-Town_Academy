package com.comeandlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeandlearn.entity.Comment;
import com.comeandlearn.resipotory.CommentRepository;
@Service
public class CommentServiceImplementation implements CommentService {
	@Autowired
	CommentRepository commentRepository;

	@Override
	public String addComment(Comment comment ) {
		commentRepository.save(comment);
		return "comment added";
	}

	@Override
	public List<Comment> getComments() {
		return commentRepository.findAll();
	}

}
