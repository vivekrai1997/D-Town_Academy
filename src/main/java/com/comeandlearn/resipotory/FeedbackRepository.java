package com.comeandlearn.resipotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
