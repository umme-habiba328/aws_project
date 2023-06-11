package com.academy.finalProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "loggedinusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoggedInUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String userName;
	
	@NotBlank
	@Column(unique = true)
	private String accessToken;
	
	@NotBlank
	@Column(unique = true)
	private String refreshToken;
}
