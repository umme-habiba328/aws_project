package com.academy.finalProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.DTO.EmployeeDTO;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.entity.Trainer;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.service.EmployeeFactory;
import com.academy.finalProject.service.TrainerService;

@RestController
@RequestMapping("/api")
public class TrainerController {

	@Autowired
	TrainerService trainerService;
	
	@Autowired 
	EmployeeFactory employeeFactory;
	
	@PutMapping("/trainer")
	public ResponseEntity<Object> updateTrainer(@Valid @RequestBody Trainer trainer, BindingResult bindingResult) throws CredentialsAlreadyExistsException, MethodArgumentNotValidException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		trainerService.updateTrainerService(trainer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/trainer")
	public ResponseEntity<Object> getTrainer(@RequestParam String name){
		return new ResponseEntity<>(trainerService.getTrainerService(name),HttpStatus.OK);
	}

	@DeleteMapping("/trainer")
	public ResponseEntity<Object> deleteTrainer(@RequestParam Long id) throws IllegalArgumentException {
		trainerService.deleteTrainerService(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
