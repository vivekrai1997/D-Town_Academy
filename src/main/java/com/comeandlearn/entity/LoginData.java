package com.comeandlearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoginData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "user_name")
	String userName;
	
	@Column(name = "email_id")
	String email;
	
	@Column(name = "role")
	String role;
	
	@Column(name = "date_time")
	String dateAndTime;

	public LoginData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginData(Long id, String userName, String email, String role, String dateAndTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.role = role;
		this.dateAndTime = dateAndTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	
}
