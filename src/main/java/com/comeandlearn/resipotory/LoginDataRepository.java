package com.comeandlearn.resipotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeandlearn.entity.LoginData;

public interface LoginDataRepository extends JpaRepository<LoginData, Long>{

}
