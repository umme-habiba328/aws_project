package com.academy.finalProject.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.entity.Course;
import com.academy.finalProject.entity.Trainer;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.repository.CourseRepository;
import com.academy.finalProject.repository.TrainerRepository;

@Service
public class TrainerService {

	@Autowired
	TrainerRepository trainerRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	public List<Trainer> getAllTrainer(){
		return trainerRepository.findAll();
	}

	public void addTrainerService(Trainer trainer) {
		// TODO Auto-generated method stub
		trainerRepository.save(trainer);
	}

	public Trainer updateTrainerService(Trainer trainer) throws CredentialsAlreadyExistsException {
		return trainerRepository.save(trainer);
	}

	public Trainer getTrainerService(String name) {
		// TODO Auto-generated method stub
		return trainerRepository.findByName(name);
	}

	public void deleteTrainerService(Long id) {
		// TODO Auto-generated method stub
		trainerRepository.deleteById(id);
	}

	public boolean containsCourseId(Long id, Long courseId) throws Exception {
		// TODO Auto-generated method stub
		if(trainerRepository.findByIdAndCoursesId(id, courseId).isPresent()) {
			return true;
		}
		return false;
	}
}
