package com.academy.finalProject.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.academy.finalProject.DTO.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	@NotBlank(message="Course must have a title")
	private String title;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name="startDate")
	private Date startDate;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name="endDate")
	private Date endDate;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String batchName;
	
	@ManyToOne
	@JoinColumn(name="batch_id")
	private Batch batch;
}
