package com.comeandlearn.resipotory;



import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	Boolean existsById(int courseId);
}
