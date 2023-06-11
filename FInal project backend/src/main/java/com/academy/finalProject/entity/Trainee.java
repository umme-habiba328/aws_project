package com.academy.finalProject.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "trainee")
public class Trainee extends Employee{
	
	public Trainee(String name,String password,String email,String contactNumber,String role) {
		super(name,password,email,contactNumber,role);
	}
	
	public Trainee(Long id, String name, String password, String email, String contactNumber, String role) {
		super(id,name,password,email,contactNumber,role);
	}

	public Trainee(Long id, String name, String password, String email, String contactNumber, String role,Batch batch) {
		super(id,name,password,email,contactNumber,role);
		this.batch = batch;
	}
	
	public Trainee(Long id, String name, String password, String email, String contactNumber, String role,Batch batch,Course course) {
		super(id,name,password,email,contactNumber,role);
		this.batch = batch;
		this.course = course;
	}
	
	@ManyToOne
	@JoinColumn(name="batch_id")
	private Batch batch;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
}
