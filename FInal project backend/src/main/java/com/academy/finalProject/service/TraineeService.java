package com.academy.finalProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.finalProject.DTO.TraineeDTO;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.repository.TraineeRepository;

@Repository
public class TraineeService {

	@Autowired
	TraineeRepository traineeRepository;
	
	public Trainee addTraineeService(Trainee trainee) throws CredentialsAlreadyExistsException {
		return traineeRepository.save(trainee);
	}
	
	public Trainee updateTraineeService(Trainee trainee) {
		return traineeRepository.save(trainee);
	}
	
	public Trainee getTraineeService(String name) {
		Optional<Trainee> trainee = traineeRepository.findByName(name);
		System.out.println("for batch: " + trainee);
		if(trainee.isPresent()) {
			return trainee.get();
		}
		return null;
	}
	
	public TraineeDTO getTraineeForBatchService(String name) {
		return traineeRepository.getDTOByName(name).get();
	}
	
	public void deleteTraineeService(Long id) throws IllegalArgumentException {
		traineeRepository.deleteById(id);
	}
}
