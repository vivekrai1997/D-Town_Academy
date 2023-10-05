package com.comeandlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeandlearn.entity.Lesson;
import com.comeandlearn.entity.PaidCourse;
import com.comeandlearn.resipotory.CommentRepository;
import com.comeandlearn.resipotory.LessonRepository;
import com.comeandlearn.resipotory.PaidCourseRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	LessonRepository lessonRepo;
	@Autowired
	CommentRepository comRepo;
	@Autowired
	PaidCourseRepository paidCourseRepository;

	@Override
	public Lesson getMyLesson(int lessonId) {
		return lessonRepo.findById(lessonId).get();
	}

	@Override
	public String buyCourse(PaidCourse paidCourse) {
		paidCourseRepository.save(paidCourse);
		return null;
	}

	@Override
	public boolean doesPaidCourseBYEmailExist(String email) {
		return paidCourseRepository.existsByEmail(email);
	}

	@Override
	public List<PaidCourse> getALLPaidCourseDetails() {
		return paidCourseRepository.findAll();
	}

}