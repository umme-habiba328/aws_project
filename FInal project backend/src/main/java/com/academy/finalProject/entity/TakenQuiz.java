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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "taken_quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TakenQuiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime date;
	
	@Setter(AccessLevel.NONE)
	private Integer obtainedMarks;
	
	@Transient
	@JsonManagedReference
	private List<Answers> answers;
	
	@ManyToOne
	@JsonBackReference
	private Quiz quiz;
	
	@ManyToOne
	@JsonBackReference
	private Employee trainee;

	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = answers.stream().map(answer -> answer.getObtainedMarks()).mapToInt(Integer::intValue).reduce(0, (a, b) -> a+b);
	}
}
