package com.comeandlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeandlearn.entity.Feedback;
import com.comeandlearn.resipotory.FeedbackRepository;
@Service
public class FeedbackServiceImplementation implements FeedbackService {
	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public String addFeedback(Feedback feedback) {
	 feedbackRepository.save(feedback);
		return "feedback added";
	}

	@Override
	public List<Feedback> getFeedbacks() {
		return feedbackRepository.findAll();
	}

}
