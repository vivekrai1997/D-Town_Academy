package com.comeandlearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaidCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "paid_course_name")
	String paidCourseName;
	@Column(name = "paid_course_price")
	String paidCoursePrice;
	@Column(name = "student_name")
	String studentName;
	@Column(name = "emal_id")
	String email;

	@Column(name = "date_time")
	String dateAndTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaidCourseName() {
		return paidCourseName;
	}

	public void setPaidCourseName(String paidCourseName) {
		this.paidCourseName = paidCourseName;
	}

	public String getPaidCoursePrice() {
		return paidCoursePrice;
	}

	public void setPaidCoursePrice(String paidCoursePrice) {
		this.paidCoursePrice = paidCoursePrice;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public PaidCourse(int id, String paidCourseName, String paidCoursePrice, String studentName, String email,
			String dateAndTime) {
		super();
		this.id = id;
		this.paidCourseName = paidCourseName;
		this.paidCoursePrice = paidCoursePrice;
		this.studentName = studentName;
		this.email = email;
		this.dateAndTime = dateAndTime;
	}

	public PaidCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PaidCourse [id=" + id + ", paidCourseName=" + paidCourseName + ", paidCoursePrice=" + paidCoursePrice
				+ ", studentName=" + studentName + ", email=" + email + "]";
	}

}
