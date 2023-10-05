package com.comeandlearn.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comeandlearn.entity.Comment;
import com.comeandlearn.entity.Course;
import com.comeandlearn.entity.PaidCourse;
import com.comeandlearn.entity.Users;
import com.comeandlearn.services.CommentService;
import com.comeandlearn.services.StudentService;
import com.comeandlearn.services.TrainerService;
import com.comeandlearn.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	TrainerService trainerService;
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;

	// Show all courses available for (Student page & Trainer page)
	@GetMapping("/getAllCoursesForUsers")
	public String getAllCourses(Model model, HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users u1 = userService.getUserByEmail(email);
			String userRole = u1.getRole();
			String userName = u1.getName();
			String mobile = u1.getMobile();
			model.addAttribute("mobile", mobile);
			model.addAttribute("email", email);
			List<Course> courselist = trainerService.getAllCourse();
			model.addAttribute("clist", courselist);
			if (userRole.equalsIgnoreCase("trainer")) {
				model.addAttribute("userName", userName);
				return "courseDetailsForTrainer";
			} else {
				model.addAttribute("userName", userName);
				return "courseDetailsForStudent";
			}
		}
		return "login";
	}

	@PostMapping("/buyCourse")
	public String buyCourse(@RequestParam("paidCourseName") String paidCourseName,
			@RequestParam("paidCoursePrice") String paidCoursePrice, HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		if (session != null && session.getAttribute("email") != null && paidCourseName != null
				&& studentService.doesPaidCourseBYEmailExist(email) == false) {
			String paidEmail = (String) session.getAttribute("email");
			String studentName = (String) session.getAttribute("userName");

			session.setAttribute("paidCourseName", paidCourseName);
			session.setAttribute("paidCoursePrice", paidCoursePrice);
			session.setAttribute("studentName", studentName);
			session.setAttribute("paidEmail", paidEmail);

			model.addAttribute("paidCourseName", paidCourseName);
			model.addAttribute("paidCoursePrice", paidCoursePrice);
			model.addAttribute("studentName", studentName);
			model.addAttribute("paidEmail", paidEmail);
			return "billing";
		} else if (studentService.doesPaidCourseBYEmailExist(email) == true) {
			return "redirect:/getPaidVideo";
		}
		return "login";
	}

	@GetMapping("/getPaidVideo")
	public String getPaidVideo(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		String userName = (String) session.getAttribute("userName");
		if (commentService.getComments() != null) {
			List<Comment> comments = commentService.getComments();
			model.addAttribute("comments", comments);
		}

		 if (session != null && session.getAttribute("email") != null
				&& session.getAttribute("paidEmail") == session.getAttribute("email")
				&& studentService.doesPaidCourseBYEmailExist(email) == false) {

			String paidEmail = (String) session.getAttribute("paidEmail");
			String studentName = (String) session.getAttribute("studentName");

			String paidCourseName = (String) session.getAttribute("paidCourseName");
			String paidCoursePrice = (String) session.getAttribute("paidCoursePrice");
			PaidCourse paidCourse = new PaidCourse();
			paidCourse.setPaidCourseName(paidCourseName);
			paidCourse.setPaidCoursePrice(paidCoursePrice);
			paidCourse.setStudentName(studentName);
			paidCourse.setEmail(paidEmail);
			
			ZoneId zoneId = ZoneId.of("Asia/Kolkata");
			ZonedDateTime dateAndTime = ZonedDateTime.now(zoneId);
			String pattern = "dd-MM-yyyy HH:mm:ss"; // pattern of my desired format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			String formattedDateTime = dateAndTime.format(formatter);
			paidCourse.setDateAndTime(formattedDateTime);
			studentService.buyCourse(paidCourse);
			
			session.removeAttribute(paidCourseName);
			session.removeAttribute(paidCoursePrice);
			session.removeAttribute(studentName);
			session.removeAttribute(paidEmail);
			model.addAttribute("paidCourseName", paidCourseName);
			model.addAttribute("paidCoursePrice", paidCoursePrice);
			model.addAttribute("studentName", studentName);
			model.addAttribute("email", email);
			model.addAttribute("userName", userName);
			model.addAttribute(session.getAttribute("userName"));
			return "paidVideo";
		} else if (studentService.doesPaidCourseBYEmailExist(email) == true) {
			model.addAttribute("email", email);
			model.addAttribute("userName", userName);
			return "paidVideo";
		}
		return "redirect:/getAllCoursesForUsers";
	}

}
