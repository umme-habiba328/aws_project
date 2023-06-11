package com.academy.finalProject.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.academy.finalProject.entity.LoggedInUser;
import com.academy.finalProject.model.AuthenticationRequest;
import com.academy.finalProject.model.JWTResponse;
import com.academy.finalProject.model.JWTUtil;
import com.academy.finalProject.repository.LoggedInUserRepository;
import com.academy.finalProject.service.CustomUserDetailsService;
import com.academy.finalProject.service.LoggedInUserService;

@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtutil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private LoggedInUserService loggedInUserService;

	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Username or password incorrect");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String accessToken = jwtutil.generateAccessToken(userDetails);
		String refreshToken = jwtutil.generateRefeshToken(userDetails);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		String role = null;
		for(GrantedAuthority g: authorities) {
			if(g.getAuthority().equals("ROLE_ADMIN")) {
				role = "ROLE_ADMIN";
			}
			else if(g.getAuthority().equals("ROLE_TRAINER")) {
				role = "ROLE_TRAINER";
			}
			else if(g.getAuthority().equals("ROLE_TRAINEE")) {
				role = "ROLE_TRAINEE";
			}
		}
		LoggedInUser loggedInUser = new LoggedInUser();
		loggedInUser.setUserName(authenticationRequest.getUsername());
		loggedInUser.setAccessToken(accessToken);
		loggedInUser.setRefreshToken(refreshToken);
		loggedInUserService.saveLoggedInUser(loggedInUser);
		ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
		ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken).path("/api").maxAge(7*24 * 60 * 60).httpOnly(true).build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, accessCookie.toString(),refreshCookie.toString()).body(new JWTResponse(authenticationRequest.getUsername(),role));
	}
	
	@RequestMapping(value = "/userLogout",method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> logoutUser(@RequestParam String username){
		loggedInUserService.deleteUserByName(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/refreshToken",method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String username = null;
		System.out.println("cookies" + request.getCookies());
		String refreshToken = WebUtils.getCookie(request, "refreshToken").getValue();
		System.out.println("refresh: "+ refreshToken);
		if(refreshToken != null) {
			username = jwtutil.getUsernameFromToken(refreshToken);
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		String accessToken = jwtutil.generateAccessToken(userDetails);
		
		LoggedInUser loggedInUser = loggedInUserService.getUserByName(username);
		loggedInUser.setAccessToken(accessToken);
		loggedInUserService.saveLoggedInUser(loggedInUser);
		ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
		ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken).path("/api").maxAge(7*24 * 60 * 60).httpOnly(true).build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, accessCookie.toString(),refreshCookie.toString()).body(username);
	}
}
