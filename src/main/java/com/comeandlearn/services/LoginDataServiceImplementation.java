package com.comeandlearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeandlearn.entity.LoginData;
import com.comeandlearn.resipotory.LoginDataRepository;

@Service
public class LoginDataServiceImplementation implements LoginDataService {

	@Autowired
	LoginDataRepository dataRepository;

	@Override
	public LoginData saveLoginData(LoginData loginData) {
		// TODO Auto-generated method stub
		return dataRepository.save(loginData);
	}
}
