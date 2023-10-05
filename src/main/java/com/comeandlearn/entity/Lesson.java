package com.comeandlearn.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int lessonId;
	String lessonName;
	String link;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "courseMAP_id")
	Course course;
	@Column(name = "date_time")
	String dateAndTime;

	public Lesson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lesson(int lessonId, String lessonName, String link, Course course, String dateAndTime) {
		super();
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.link = link;
		this.course = course;
		this.dateAndTime = dateAndTime;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

//	@Override
//	public String toString() {
//		return "Lesson [lessonId=" + lessonId + ", lessonName=" + lessonName + ", topic=" + topic + ", link=" + link
//				+ ", course=" + course + "]";
//	}
//	Thank you for providing the code for your Course and Lesson classes. The issue with the StackOverflowError in
//	your stack trace is likely due to the cyclic references between the Course and Lesson classes in their toString methods.
//	When you call toString on a Course object, it calls toString on the associated Lesson objects, and vice versa,
//	leading to an infinite loop.

}
