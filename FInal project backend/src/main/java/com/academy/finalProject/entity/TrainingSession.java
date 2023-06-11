package com.academy.finalProject.entity;


import java.sql.Time;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.academy.finalProject.DTO.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="training_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingSession {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String topic;
	
	@NotBlank
	private String status;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date date;
	
	private Time startTime;
	
	private Time endTime;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="trainer_id")
	private Employee trainer;
}
