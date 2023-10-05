package com.comeandlearn.services;

import java.util.List;

import com.comeandlearn.entity.Users;

public interface UserService {

	// to do user registration
	String addUser(Users users);

	//to check whether email is correct/saved in data base
	boolean checkEmail(String email);

	//to check whether the email id and  password  matches or not
	boolean validateService(String email, String password );

	//to get the role of the user who has done log in
	Users checkUserRole(String email);

	//to get the Object of user with email
	Users getUserByEmail(String email);
	//to get all users
	List<Users>  getAllUsers();
}
