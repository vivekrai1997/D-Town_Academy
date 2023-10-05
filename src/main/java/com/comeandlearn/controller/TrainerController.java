package com.comeandlearn.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comeandlearn.entity.Comment;
import com.comeandlearn.entity.Course;
import com.comeandlearn.entity.Feedback;
import com.comeandlearn.entity.Lesson;
import com.comeandlearn.entity.LoginData;
import com.comeandlearn.entity.PaidCourse;
import com.comeandlearn.entity.Users;
import com.comeandlearn.resipotory.LoginDataRepository;
import com.comeandlearn.services.CommentService;
import com.comeandlearn.services.FeedbackService;
import com.comeandlearn.services.StudentService;
import com.comeandlearn.services.TrainerService;
import com.comeandlearn.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class TrainerController {

	@Autowired
	TrainerService trainerService;
	@Autowired
	UserService userService;
	@Autowired
	StudentService studentService;
	@Autowired
	LoginDataRepository dataRepository;
	@Autowired
	CommentService commentService;
	@Autowired
	FeedbackService feedbackService;

	@PostMapping("/addCourse")
	public String addCourse(@RequestParam("courseName") String courseName,
			@RequestParam("coursePrice") String coursePrice, @RequestParam("lessonName") String lessonName,
			@RequestParam("link") String link, Model model, HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
			Course course = new Course();
			course.setCourseName(courseName);
			course.setCoursePrice(coursePrice);
			// retrieving email Id of user who is currently logged in
			String email = (String) session.getAttribute("email");
			// retrieving user who is having this email Id
			Users user = userService.getUserByEmail(email);
			Set<Users> enrolledUsers = new HashSet<>();
			enrolledUsers.add(user);
			course.setEnrolledUsers(enrolledUsers);
			user.getEnrolledCourses().add(course);

			ZoneId zoneId = ZoneId.of("Asia/Kolkata");
			ZonedDateTime dateAndTime = ZonedDateTime.now(zoneId);
			String pattern = "dd-MM-yyyy HH:mm:ss"; // pattern of my desired format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			String formattedDateTime = dateAndTime.format(formatter);
			course.setDateAndTime(formattedDateTime);
			trainerService.addCourse(course);

			Lesson lesson = new Lesson();
			lesson.setLessonName(lessonName);
			lesson.setLink(link);
			lesson.setCourse(course);
			lesson.setDateAndTime(formattedDateTime);
			trainerService.addLesson(lesson);
			if (course.getLessons() == null) {
				course.setLessons(new ArrayList<>());
			}
			// adds course in user enrollerCourses anIdd automatically saves in data base by
			// hibernate
			course.getLessons().add(lesson);
			System.out.println("courses added");
			return "redirect:/getAddLessonPage";
		}
		return "login";
	}

	@PostMapping("/addLesson")
	public String addLesson(@RequestParam("courseId") int courseId, @RequestParam("lessonName") String lessonName,
			@RequestParam("link") String link, Model model, HttpSession session) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users user = userService.getUserByEmail(email);
			String userRole = user.getRole();
			String mobile = user.getMobile();
			model.addAttribute("mobile", mobile);
			model.addAttribute("email", email);
			if (userRole.equalsIgnoreCase("trainer")) {
				Set<Course> courselist = user.getEnrolledCourses();
				String userName = user.getName();
				model.addAttribute("clist", courselist);
				model.addAttribute("userName", userName);
				System.out.println("koko");
				if (trainerService.courseExistsById(courseId)) {
					int userId = user.getId();
					boolean foundMatch = false;
					System.out.println("forloop");
					if (courselist.isEmpty()) {
						System.out.println("empty");
					}
					for (Course course : courselist) {
						int cId = course.getCourseId();
						System.out.println("vivek");
						if (courseId == cId) {
							foundMatch = true;
							break;
						}
					}
					System.out.println("false");
					if (foundMatch == false) {
						boolean showNotice1 = true;
						model.addAttribute("showNotice1", showNotice1);
						System.out.println(
								"This Course Id belongs to other Trainer please re-enter as shown in the table");
						return "addLesson";
					}
					model.addAttribute("clist", courselist);
					model.addAttribute("userName", userName);
					Lesson lesson = new Lesson();
					lesson.setLessonName(lessonName);
					lesson.setLink(link);
					Course course = trainerService.getCourseById(courseId);
					lesson.setCourse(course);
					System.out.println("lessonId before adding lesson obj to DB :" + lesson.getLessonId());
					trainerService.addLesson(lesson); // Make sure this method persists the lesson
					System.out.println("lessonId after adding lesson obj to DB :" + lesson.getLessonId());

					System.out.println("lesson obj after added to DB:" + lesson.toString());
					course.getLessons().add(lesson);
					System.out.println("course after addinf lesson " + course);
					return "addLesson";
				}
				boolean showNotice2 = true;
				model.addAttribute("showNotice2", showNotice2);
				System.out.println("Wrong Course Id, please re-enter as shown in the table");
				return "addLesson";
			} else {
				return "/goToUserHome";
			}
		}
		return "login";
	}

	@GetMapping("/getAddCoursePage")
	public String getAddCoursePage(HttpSession session, Model model) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users user = userService.getUserByEmail(email);
			String userRole = user.getRole();
			String userName = user.getName();
			String mobile = user.getMobile();
			model.addAttribute("mobile", mobile);
			model.addAttribute("email", email);
			Set<Course> courselist = user.getEnrolledCourses();
			model.addAttribute("clist", courselist);
			if (userRole.equalsIgnoreCase("trainer")) {
				model.addAttribute("userName", userName);
				return "addCourse";
			} else {
				return null;
			}
		}
		return "login";
	}

	@GetMapping("/getAddLessonPage")
	public String getAddLessonPage(HttpSession session, Model model) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			Users user = userService.getUserByEmail(email);
			String userRole = user.getRole();
			String userName = user.getName();
			String mobile = user.getMobile();
			model.addAttribute("mobile", mobile);
			model.addAttribute("email", email);
			Set<Course> courselist = user.getEnrolledCourses();
			model.addAttribute("clist", courselist);
			if (userRole.equalsIgnoreCase("trainer")) {
				model.addAttribute("userName", userName);
				return "addLesson";
			} else {
				return null;
			}
		}
		return "login";
	}

	@GetMapping("/getAllDetailsToAdmin")
	public String getAllDetailsToAdmin(HttpSession session, Model model) {
		if (session != null && session.getAttribute("email") != null) {
			String email = (String) session.getAttribute("email");
			if (email.equals("viveksotangrai@gmail.com")) {
				Users user = userService.getUserByEmail(email);
				String userRole = user.getRole();
				String userName = user.getName();
				String mobile = user.getMobile();
				List<Course> courselist = trainerService.getAllCourse();
				model.addAttribute("clist", courselist);
				model.addAttribute("userName", userName);
				model.addAttribute("mobile", mobile);
				model.addAttribute("email", email);
				model.addAttribute("clist", courselist);
				List<Users> allUsers = userService.getAllUsers();
				List<Users> trainerList = new ArrayList<>();
				List<Users> studentList = new ArrayList<>();
				for (Users u : allUsers) {
					if (u.getRole().equalsIgnoreCase("trainer")) {
						trainerList.add(u);
					} else if (u.getRole().equalsIgnoreCase("student")) {
						studentList.add(u);
					}
				}
				model.addAttribute("trainerList", trainerList);
				model.addAttribute("studentList", studentList);
				List<PaidCourse> paidCourseList = studentService.getALLPaidCourseDetails();
				model.addAttribute("paidCourseList)", paidCourseList);
				List<LoginData> loginDataList = dataRepository.findAll();
				model.addAttribute("loginDataList", loginDataList);
				if (commentService.getComments() != null) {
					List<Comment> comments = commentService.getComments();
					model.addAttribute("comments", comments);
				} 
				if (feedbackService.getFeedbacks() != null) {
					List<Feedback> feedbacks = feedbackService.getFeedbacks();
					model.addAttribute("feedbacks", feedbacks);
				}
				return "allDetails";
			}
			return "redirect:/getAllCoursesForUsers";
		}
		return "login";
	}
}
