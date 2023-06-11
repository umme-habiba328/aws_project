package com.academy.finalProject.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.academy.finalProject.entity.Employee;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	EmployeeService employeeService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee;
		try {
			employee = employeeService.getEmployeeByName(username);
		}catch(Exception ex) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(employee.getRole()));
		return new User(employee.getName(),employee.getPassword(),authorities);
	}

}
