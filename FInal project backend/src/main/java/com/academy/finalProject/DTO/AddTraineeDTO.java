package com.academy.finalProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTraineeDTO {

	private Long id;
	private String name;
	private String email;
	private Long courseId;
}
