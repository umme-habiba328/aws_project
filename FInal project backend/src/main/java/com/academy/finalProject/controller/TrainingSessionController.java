package com.academy.finalProject.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.DTO.AddTrainingSessionDTO;
import com.academy.finalProject.DTO.TrainingSessionDTO;
import com.academy.finalProject.service.TrainingSessionService;

@RestController
@RequestMapping("/api/trainingSession")
public class TrainingSessionController {
	
	@Autowired
	TrainingSessionService trainingSessionService;

	@PostMapping("")
	public ResponseEntity<Object> addTrainingSession(@Valid @RequestBody AddTrainingSessionDTO addTrainingSessionDTO, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		trainingSessionService.addTrainingSessionService(addTrainingSessionDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/table")
	public ResponseEntity<Object> getTrainingSession(@RequestParam String date,@RequestParam String course){
		List<TrainingSessionDTO> trainingSessions = null;
		Date d;
		System.out.println(date);
		if(course.length() > 0 && date.length()> 0) {
			d = Date.valueOf(date);
			trainingSessions = trainingSessionService.getTrainingSessionForCourse(course, d);
		}else if(date.length() > 0) {
			d = Date.valueOf(date);
			trainingSessions = trainingSessionService.getTrainingSessionByDate(d);
		}
		else if(course.length()>0){
			trainingSessions = trainingSessionService.getTrainingSessionByCourse(course);
		}
		return new ResponseEntity<>(trainingSessions,HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getTrainingSessionById(@RequestParam Long id){
		TrainingSessionDTO trainingSession = trainingSessionService.getById(id);
		return new ResponseEntity<>(trainingSession,HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateTrainingSessionById(@RequestBody AddTrainingSessionDTO addTrainingSessionDTO) throws Exception{
		System.out.println(addTrainingSessionDTO);
		trainingSessionService.updateTrainingSession(addTrainingSessionDTO);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Object> deleteTrainingSession(@RequestParam Long id){
		trainingSessionService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}