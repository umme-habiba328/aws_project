package com.academy.finalProject.DTO;

import java.time.LocalDateTime;

public interface GetQuizPerformanceDTO {

	String getTopic();
	String getCourseName();
	Integer getMarks();
	String getTraineeName();
	LocalDateTime getDate();
}
