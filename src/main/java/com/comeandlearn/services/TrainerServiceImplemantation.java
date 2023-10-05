package com.comeandlearn.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comeandlearn.entity.Course;
import com.comeandlearn.entity.Lesson;
import com.comeandlearn.entity.Users;
import com.comeandlearn.resipotory.CourseRepository;
import com.comeandlearn.resipotory.LessonRepository;
import com.comeandlearn.resipotory.UserRepository;

@Service
public class TrainerServiceImplemantation implements TrainerService {

	@Autowired
	CourseRepository crepo;
	@Autowired
	LessonRepository lrepo;
	@Autowired
	UserRepository urepo;

	@Override
	public String addCourse(Course course) {
		crepo.save(course);
		return null;
	}

	@Override
	public Course getCourseById(int courseId) {
		return crepo.findById(courseId).get();
//		get() in the getCourseById method is appropriate because it's used to retrieve a single entity by its unique identifier. 
//    On the other hand, in the getCoursesEnrolledByUser method,
//		we are returning a set of entities, so there's no need to call get()
	}

	@Transactional
	@Override
	public String addLesson(Lesson lesson) {
		lrepo.save(lesson);
		return null;
	}

	@Override
	public List<Course> getAllCourse() {

		return crepo.findAll();
	}

	@Override
	public Set<Course> getCoursesEnrolledByUser(String email) {
		Users user = urepo.getUserByEmail(email);
		return user.getEnrolledCourses();
	}

	@Override
	@Transactional
	public void addCoursesToUser(Users user) {
		urepo.save(user);
	}

	@Override
	public Boolean courseExistsById(int courseId) {
		return crepo.existsById(courseId);
	}

}
