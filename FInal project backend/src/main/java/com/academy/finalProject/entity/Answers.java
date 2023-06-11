package com.academy.finalProject.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String answerOption;
	
	@Setter(AccessLevel.NONE)
	private int obtainedMarks;
	
	@ManyToOne
	@JsonBackReference
	private TakenQuiz takenQuiz;
	
	@OneToOne
	@JsonBackReference
	private Questions question;
	
	public void setObtainedMarks(int obtainedMarks) {
		if(question.getAnswerOption().equals(answerOption)) {
			this.obtainedMarks = question.getMarks();
		}
		else {
			this.obtainedMarks = 0;
		}
	}
}