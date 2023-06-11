package com.academy.finalProject.DTO;

import java.util.List;

import com.academy.finalProject.entity.Batch;
import com.academy.finalProject.entity.Trainee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTraineesToBatchDTO {

	private AddBatchDTO batch;
	
	private List<AddTraineeDTO> trainees;
}
