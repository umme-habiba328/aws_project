package com.academy.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.academy.finalProject.entity.LoggedInUser;
import com.academy.finalProject.repository.LoggedInUserRepository;

@Service
public class LoggedInUserService {
	
	@Autowired
	private LoggedInUserRepository loggedInUserRepository;

	public boolean isAccessTokenRight(String userName,String accessToken) {
		return compareAccessToken(userName,accessToken);
	}
	
	private boolean compareAccessToken(String userName,String accessToken) {
		String savedAccessToken = getUserByName(userName).getAccessToken();
		return savedAccessToken.equals(accessToken);
	}
	
	public void deleteUserByName(String userName) {
		deleteByName(userName);
	}
	@CacheEvict(cacheNames="loggedInUser",key="#userName")
	private void deleteByName(String userName) {
		LoggedInUser alreadyLoggedIn = loggedInUserRepository.findByUserName(userName);
		if(alreadyLoggedIn != null) {
			loggedInUserRepository.delete(alreadyLoggedIn);
		}
	}

	@CachePut(cacheNames="loggedInUsers",key="#loggedInUser.userName")
	public void saveLoggedInUser(LoggedInUser loggedInUser) {
		loggedInUserRepository.save(loggedInUser);
	}

	@Cacheable(cacheNames="loggedInUsers")
	public LoggedInUser getUserByName(String userName) {
		
		return loggedInUserRepository.findByUserName(userName);
	}
}
