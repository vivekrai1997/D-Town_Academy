package com.comeandlearn.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.comeandlearn.entity.LoginData;
import com.comeandlearn.entity.Users;
import com.comeandlearn.resipotory.CourseRepository;
import com.comeandlearn.resipotory.LessonRepository;
import com.comeandlearn.services.CommentService;
import com.comeandlearn.services.LoginDataService;
import com.comeandlearn.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ServiceController {
	@Autowired
	UserService userService;
	@Autowired
	CommentService comService;
	@Autowired
	LoginDataService dataService;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	CourseRepository courseRepository;

	// Registration
	@PostMapping("/addUser")
	public String addUser(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("mobile") String mobile,
			@RequestParam("role") String role, Model model) {
		boolean emailExists = userService.checkEmail(email);
		if (!emailExists) {
			Users user = new Users();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setMobile(mobile);
			user.setRole(role);
			ZoneId zoneId = ZoneId.of("Asia/Kolkata");
			ZonedDateTime dateAndTime = ZonedDateTime.now(zoneId);
			String pattern = "dd-MM-yyyy HH:mm:ss"; // pattern of my desired format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			String formattedDateTime = dateAndTime.format(formatter);
			user.setDateAndTime(formattedDateTime);
			user.setEnrolledCourses(null);
			userService.addUser(user);
			return "login";
		} else {
			System.out.println("Email id already exists");
			model.addAttribute("error1", true);
			return "register";
		}
	}

// Login page
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		if (userService.checkEmail(email)) {
			if (userService.validateService(email, password)) {
				System.out.println("Login successful");
				Users u1 = userService.checkUserRole(email);
				String userName = u1.getName();
				String role = u1.getRole();
				LoginData loginData = new LoginData();
				loginData.setUserName(userName);
				loginData.setEmail(email);
				loginData.setRole(role);
				ZoneId zoneId = ZoneId.of("Asia/Kolkata");
				ZonedDateTime dateAndTime = ZonedDateTime.now(zoneId);
				String pattern = "dd-MM-yyyy HH:mm:ss"; // pattern of my desired format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
				String formattedDateTime = dateAndTime.format(formatter);
				loginData.setDateAndTime(formattedDateTime);
				session.setAttribute("email", email);
				session.setAttribute("userName", userName);
				dataService.saveLoginData(loginData);
				return "redirect:/goToUserHome";
			} else {
				System.out.println("Wrong credentials");
				model.addAttribute("error1", true);
				return "login";
			}
		} else {
			System.out.println("No account with this Email id, please check your id");
			model.addAttribute("error2", true);
			return "login"; // Redirect back to the login page with an error message
		}
	}

	@GetMapping("/logOut")
	public String logOut(HttpSession session, Model model) {
		if (session != null && session.getAttribute("email") != null) {
			session.invalidate();
			return "login";
		}
		return "login";
	}


	
//	goes to user home when clicked home because we have
//	made different home for Student & Trainer
	@GetMapping("/goToUserHome")
	public String goToUserHome(HttpSession session, Model model) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users u1 = userService.getUserByEmail(email);
			String userRole = u1.getRole();
			String userName = u1.getName();
			String mobile = u1.getMobile();
			model.addAttribute("mobile", mobile);
			model.addAttribute("email", email);
			model.addAttribute("userName", userName);
			if (email.equals("viveksotangrai@gmail.com")) {
				return "adminHome";
			} else if (userRole.equalsIgnoreCase("trainer")) {
				return "trainerHome";
			} else {
				return "studentHome";
			}
		}return "index";
	}


}
