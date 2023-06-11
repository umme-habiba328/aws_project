package com.academy.finalProject.DTO;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.academy.finalProject.entity.Batch;
import com.academy.finalProject.entity.Course;
import com.academy.finalProject.entity.Trainer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddTrainingSessionDTO {

	private Long id;
	
	@NotBlank
	private String topic;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String date;
	
	@NotBlank
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm", timezone = "UTC")
	private String startTime;
	
	@NotBlank
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm", timezone = "UTC")
	private String endTime;
	
	@NotBlank
	private String course;
	
	@NotBlank
	private String trainer;
}
