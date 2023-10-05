package com.comeandlearn.services;

import java.util.List;

import com.comeandlearn.entity.Feedback;

public interface FeedbackService  {
	String addFeedback(Feedback feedback);
	List<Feedback> getFeedbacks();

}
