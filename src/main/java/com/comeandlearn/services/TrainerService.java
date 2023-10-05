package com.comeandlearn.services;

import java.util.List;
import java.util.Set;

import com.comeandlearn.entity.Course;
import com.comeandlearn.entity.Lesson;
import com.comeandlearn.entity.Users;

public interface TrainerService {
	String addCourse(Course course);
	//Course getCourseById(int courseId );
	Course getCourseById(int courseId);
	String addLesson(Lesson lesson);
	List<Course> getAllCourse( );
	Set<Course> getCoursesEnrolledByUser(String email);
	void addCoursesToUser(Users user );
	Boolean courseExistsById(int courseId);
}
