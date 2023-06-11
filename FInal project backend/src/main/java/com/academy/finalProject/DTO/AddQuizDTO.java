package com.academy.finalProject.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.academy.finalProject.entity.Options;
import com.academy.finalProject.entity.Questions;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
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
public class AddQuizDTO {

	private Long id;
	
	private String topic;
	
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	private LocalDateTime startTime;
	
	private List<QuestionDTO> questions;
	
	private String course;
}
