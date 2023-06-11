package com.academy.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.AddTraineesToBatchDTO;
import com.academy.finalProject.entity.Batch;
@Service
public class BatchAdapter {
	
	@Autowired
	BatchService batchService;
	
	@Autowired
	TraineeService traineeService;

	public void addTraineesToBatchAdapter(AddTraineesToBatchDTO addTraineesToBatchDTO) {
		Batch batch = batchService.getBatchById(addTraineesToBatchDTO.getBatch().getId());
		addTraineesToBatchDTO.getTrainees().stream()
				.map(trainee -> traineeService.getTraineeService(trainee.getName()))
				.map(trainee -> {trainee.setBatch(batch);
					return trainee;}).forEach(trainee -> traineeService.updateTraineeService(trainee));
	}
}
