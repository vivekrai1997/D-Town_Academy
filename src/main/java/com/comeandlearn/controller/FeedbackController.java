package com.comeandlearn.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comeandlearn.entity.Feedback;
import com.comeandlearn.entity.Users;
import com.comeandlearn.services.FeedbackService;
import com.comeandlearn.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FeedbackController {
	@Autowired
	UserService userService;
	@Autowired
	FeedbackService feedbackService;

	@PostMapping("/addFeedback")
	public String addComment(@RequestParam("content") String content, HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users u1 = userService.getUserByEmail(email);
			String userName = u1.getName();
			if (u1.getRole().equalsIgnoreCase("student")) {
				Feedback feedback = new Feedback();
				feedback.setUserName(userName);
				feedback.setContent(content);
				ZoneId zoneId = ZoneId.of("Asia/Kolkata");
				ZonedDateTime dateAndTime = ZonedDateTime.now(zoneId);
				String pattern = "dd-MM-yyyy HH:mm:ss"; // pattern of my desired format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
				String formattedDateTime = dateAndTime.format(formatter);
				feedback.setDateAndTime(formattedDateTime);
				feedbackService.addFeedback(feedback);
				return "redirect:/goToUserHome";
			}
		}
		return "login";
	}

}
