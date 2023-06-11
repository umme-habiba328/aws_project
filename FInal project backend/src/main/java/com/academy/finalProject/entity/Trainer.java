package com.academy.finalProject.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "trainer")
public class Trainer extends Employee{
	
	public Trainer(String name,String password,String email,String contactNumber,String role) {
		super(name,password,email,contactNumber,role);
	}
	
	public Trainer(Long id, String name, String password, String email, String contactNumber, String role) {
		// TODO Auto-generated constructor stub
		super(id,name,password,email,contactNumber,role);
	}

	@ManyToMany
	private List<Course> courses;
}
