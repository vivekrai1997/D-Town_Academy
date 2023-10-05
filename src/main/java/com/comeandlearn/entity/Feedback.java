package com.comeandlearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int feedbackId;
	
	String userName;
	
	@Column(length = 100)
	String content;
	
	@Column(name = "date_time")
	String dateAndTime;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(int feedbackId, String userName, String content, String dateAndTime) {
		super();
		this.feedbackId = feedbackId;
		this.userName = userName;
		this.content = content;
		this.dateAndTime = dateAndTime;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
 
	
}
