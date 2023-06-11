package com.academy.finalProject.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.academy.finalProject.entity.Answers;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Quiz;

public interface GetTakenQuizDTO {

	Long getId();
	
	LocalDateTime getDate();
	
	String getTopic();
	
	Integer getObtainedMarks();
	
	Integer getTotalMarks();
}
