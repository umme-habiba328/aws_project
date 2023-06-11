package com.academy.finalProject.entity;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;



import com.academy.finalProject.DTO.CustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String topic;
	
	private LocalDateTime startTime;
	
	@Setter(AccessLevel.NONE)
	private Integer totalMarks;
	
	private String status;
	
	@Transient
	@JsonManagedReference
	private List<Questions> questions;
	
	@ManyToOne
	@JsonBackReference
	private Course course;
	
	@ManyToOne
	@JsonBackReference
	private Employee trainer;
	
	public void setTotalMarks() {
		this.totalMarks = questions.stream().map(question -> question.getMarks()).mapToInt(Integer::intValue).reduce(0, (a, b) -> a+b);
		System.out.println("marks: "+totalMarks);
	}
}
