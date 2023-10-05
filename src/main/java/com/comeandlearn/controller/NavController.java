package com.comeandlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {

	//Display home page with notice
	@GetMapping("/")
	public String index(HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "index";
		}
		return "redirect:/goToUserHome";
	}

	@GetMapping("/login")
	public String login(HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
		//String email = (String) session.getAttribute("email");
			return "redirect:/goToUserHome";
		} 
		return "login";
	}

	@GetMapping("/register")
	public String register(HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
				return "redirect:/goToUserHome";
			} 
		return "register";
	}
	
	@GetMapping("/createCourse")
	public String createCourse () {
		return "createCourse";
	}

}
