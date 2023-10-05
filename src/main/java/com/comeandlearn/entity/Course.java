package com.comeandlearn.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int courseId;
	String courseName;
	String coursePrice;

	@OneToMany(mappedBy = "course")
	List<Lesson> lessons;

	@ManyToMany(mappedBy = "enrolledCourses", fetch = FetchType.EAGER)
	Set<Users> enrolledUsers;
	@Column(name = "date_time")
	String dateAndTime;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Set<Users> getEnrolledUsers() {
		return enrolledUsers;
	}

	public void setEnrolledUsers(Set<Users> enrolledUsers) {
		this.enrolledUsers = enrolledUsers;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Course(int courseId, String courseName, String coursePrice, List<Lesson> lessons, Set<Users> enrolledUsers,
			String dateAndTime) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		this.lessons = lessons;
		this.enrolledUsers = enrolledUsers;
		this.dateAndTime = dateAndTime;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", coursePrice=" + coursePrice
				+ ", lessons=" + lessons + ", enrolledUsers=" + enrolledUsers + "]";
	}

//	@Override
//	public String toString() {
//		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", coursePrice=" + coursePrice
//				+ ", lessons=" + lessons + ", enrolledUsers=" + enrolledUsers + "]";
//	}
//	Thank you for providing the code for your Course and Lesson classes. The issue with the StackOverflowError in
//	your stack trace is likely due to the cyclic references between the Course and Lesson classes in their toString methods.
//	When you call toString on a Course object, it calls toString on the associated Lesson objects, and vice versa,
//	leading to an infinite loop.

}
