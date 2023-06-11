package com.academy.finalProject.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.academy.finalProject.entity.Questions;

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
public class QuestionDTO {
	
	private Long id;
	
	@NotBlank
	private String questionDescription;
	
	@NotBlank
	private Integer marks;
	
	@NotBlank
	private String answer;
	
	private List<String> options;
}
