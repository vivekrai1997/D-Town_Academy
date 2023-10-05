package com.comeandlearn.services;


import java.util.List;
import java.util.Set;

import com.comeandlearn.entity.Lesson;
import com.comeandlearn.entity.PaidCourse;

public interface StudentService {
	Lesson getMyLesson(int lessonId);
	String buyCourse(PaidCourse paidCourse);
	List<PaidCourse> getALLPaidCourseDetails();
	boolean doesPaidCourseBYEmailExist(String email);


}
