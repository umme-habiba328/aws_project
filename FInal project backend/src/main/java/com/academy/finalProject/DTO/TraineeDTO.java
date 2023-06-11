package com.academy.finalProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public interface TraineeDTO {
	Long getId();
	String getName();
	String getEmail();
	String getCourseId();
}
