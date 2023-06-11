package com.academy.finalProject.DTO;

import java.time.LocalDateTime;

public interface QuizDTO {

	Long getId();
	String getStatus();
	String getCourseName();
	LocalDateTime getStartTime();
	String getTopic();
}
