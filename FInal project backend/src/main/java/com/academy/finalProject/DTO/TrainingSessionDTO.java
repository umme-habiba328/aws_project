package com.academy.finalProject.DTO;

import java.sql.Time;
import java.sql.Date;

public interface TrainingSessionDTO {

	Long getId();
	
	String getCourseTitle();
	
	String getTopic();
	
	String getStatus();
	
	Date getDate();
	
	Time getStartTime();
	
	Time getEndTime();
	
	String getTrainerName();
}
