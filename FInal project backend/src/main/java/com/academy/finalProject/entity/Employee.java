package com.academy.finalProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	
	public Employee(String name,String password,String email,String contactNumber,String role) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name",unique=true)
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
	
	private String role;
}
