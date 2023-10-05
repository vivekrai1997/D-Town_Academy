package com.comeandlearn.resipotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.PaidCourse;

public interface PaidCourseRepository extends JpaRepository<PaidCourse, Integer> {
	boolean existsByEmail(String email);


}
