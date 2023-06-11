package com.academy.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.finalProject.entity.LoggedInUser;

public interface LoggedInUserRepository extends JpaRepository<LoggedInUser, Long> {

	public LoggedInUser findByUserName(String username);
}
