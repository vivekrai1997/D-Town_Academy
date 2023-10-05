package com.comeandlearn.resipotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
