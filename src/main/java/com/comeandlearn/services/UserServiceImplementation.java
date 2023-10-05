package com.comeandlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeandlearn.entity.Users;
import com.comeandlearn.resipotory.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repo;

	@Override
	public String addUser(Users users) {
		repo.save(users);
		return "User added successfully";
	}

	@Override
	public boolean checkEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public boolean validateService(String email, String password) {
		if (repo.existsByEmail(email)) {
			Users u = repo.getUserByEmail(email);
			String dbPassword = u.getPassword();
			if (password.equals(dbPassword)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public Users checkUserRole(String email) {
		return repo.getUserByEmail(email);
	}

	@Override
	public Users getUserByEmail(String email) {
		return repo.getUserByEmail(email);
	}

	@Override
	public List<Users> getAllUsers() {
		return repo.findAll();
	}

}
