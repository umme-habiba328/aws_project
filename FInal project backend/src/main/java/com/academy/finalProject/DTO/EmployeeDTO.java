package com.academy.finalProject.DTO;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	
	public EmployeeDTO(Long id,String name,String password,String email,String contactNumber,String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
	}
	
	public EmployeeDTO(String name,String password,String email,String contactNumber,String role) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
	}
	
	public EmployeeDTO(String name,String password,String email,String contactNumber,String role,String batch) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.batch = batch;
	}
	public EmployeeDTO(Long id,String name,String password,String email,String contactNumber,String role,String batch) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.batch = batch;
	}
	
	public EmployeeDTO(Long id,String name,String password,String email,String contactNumber,String role,String batch,String course) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.batch = batch;
		this.course = course;
	}
	
	private Long id;

	@Column(name="name")
	@NotBlank(message="Name is mandatory")
	@Pattern(regexp="^[a-zA-Z0-9][a-zA-Z0-9_]*",message="Name must not contain any special character except '_' .")
	private String name;
	
	@Column(name="password")
	@NotBlank(message="Password is mandatory")
	@Size(min=8, message="Password must be atleast 8 characters long")
	@Pattern(regexp="^[a-zA-Z0-9][a-zA-Z0-9@#._]*",message="Password cannot start with special character or have special character except @/#/./_")
	private String password;
	
	@Column(name="email", unique=true)
	@NotBlank(message="Email is mandatory")
	@Email(message = "Email is invalid")
	private String email;
	
	@Column(name = "contactNumber",unique = true)
	@NotBlank(message= "Contact number is mandatory")
	@Size(min=14,max=14,message="Length must be 14")
	@Pattern(regexp= "^[+][8][8][0](1|3)\\d{9}$",message="Contact number should start with +8801/+8803")
	private String contactNumber;
	
	private List<String> courses;
	
	@NotBlank
	private String role;
	
	private String batch;
	
	private String course;
}
