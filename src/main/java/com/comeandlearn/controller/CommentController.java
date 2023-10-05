package com.comeandlearn.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.comeandlearn.entity.Comment;
import com.comeandlearn.entity.Users;
import com.comeandlearn.resipotory.CommentRepository;
import com.comeandlearn.services.CommentService;
import com.comeandlearn.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class CommentController {
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;

	@GetMapping("/showComments")
	public String getComments(Model model, HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users u1 = userService.getUserByEmail(email);
			if (u1.getRole().equalsIgnoreCase("student")) {
				model.addAttribute("email", email);
				model.addAttribute("userName", session.getAttribute("userName"));
				if (commentService.getComments() != null) {
					List<Comment> comments = commentService.getComments();
					model.addAttribute("comments", comments);
				}
				return "paidVideo";
			}
		}
		return "login";
	}

	
	@PostMapping("/addComment")
	public String addComment(@RequestParam("content") String content, HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users u1 = userService.getUserByEmail(email);
			String userName = u1.getName();
			if (u1.getRole().equalsIgnoreCase("student")) {
				Comment comment = new Comment();
				comment.setUserName(userName);
				comment.setContent(content);
				ZoneId zoneId = ZoneId.of("Asia/Kolkata");
				ZonedDateTime dateAndTime = ZonedDateTime.now(zoneId);
				String pattern = "dd-MM-yyyy HH:mm:ss"; // pattern of my desired format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
				String formattedDateTime = dateAndTime.format(formatter);
				comment.setDateAndTime(formattedDateTime);
				commentService.addComment(comment);
				return "redirect:/showComments";
			}
		}
		return "login";
	}
}
