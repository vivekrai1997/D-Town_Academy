package com.comeandlearn.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String email;
	String password;
	String mobile;
	String role;
	@Column(name = "date_time")
	String dateAndTime;



	@ManyToMany(fetch = FetchType.EAGER,   cascade = CascadeType.ALL)
	@JoinTable(name = "user_course",
							joinColumns = @JoinColumn(name =  "user_id"),
							inverseJoinColumns = @JoinColumn(name = "course_id"))
	Set<Course> enrolledCourses;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Users(int id, String name, String email, String password, String mobile, String role,
			String dateAndTime, Set<Course> enrolledCourses) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
		this.dateAndTime = dateAndTime;
		this.enrolledCourses = enrolledCourses;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getDateAndTime() {
		return dateAndTime;
	}


	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + ", role=" + role + ", dateAndTime=" + dateAndTime 
				+ "]";
	}
	public Users(Set<Course> enrolledCourses) {
		super();
		this.enrolledCourses = enrolledCourses;
	}
	public Set<Course> getEnrolledCourses() {
		return enrolledCourses;
	}
	public void setEnrolledCourses(Set<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}




}
