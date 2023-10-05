package com.comeandlearn.resipotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.Users;

public interface UserRepository extends  JpaRepository<Users, Integer> {

	boolean existsByEmail(String email);
	Users getUserByEmail(String email );

}
