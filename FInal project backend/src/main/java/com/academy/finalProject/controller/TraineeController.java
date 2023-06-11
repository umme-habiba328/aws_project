package com.academy.finalProject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.service.BatchService;
import com.academy.finalProject.service.CourseService;
import com.academy.finalProject.service.EmployeeService;
import com.academy.finalProject.service.TraineeService;
import com.academy.finalProject.service.TrainerService;

@RestController
@RequestMapping("/api/trainee")
public class TraineeController {

	@Autowired
	TraineeService traineeService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	BatchService batchService;
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/removeTraineeBatch")
	public ResponseEntity<Object> removeTraineeBatch(@RequestParam String name){
		Trainee trainee = traineeService.getTraineeService(name);
		System.out.println(trainee);
		trainee.setBatch(null);
		traineeService.updateTraineeService(trainee);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/traineeForBatch")
	public ResponseEntity<Object> getTraineeForBatch(@RequestParam String name,@RequestParam String batch){
		Trainee trainee = traineeService.getTraineeService(name);
		trainee.setBatch(batchService.getBatchService(batch));
		traineeService.updateTraineeService(trainee);
		return new ResponseEntity<>(traineeService.getTraineeForBatchService(name),HttpStatus.OK);
	}
	
	@GetMapping("/allTrainees")
	public ResponseEntity<Object> getTraineesForCourse(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Trainee trainee = traineeService.getTraineeService(name);
		Long courseId = trainee.getCourse().getId();
		Long batchId = trainee.getBatch().getId();
		return new ResponseEntity<>(courseService.getAllTraineesOfCourse(courseId,batchId),HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getCurrentEmployee(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<>(employeeService.getEmployeeByName(name),HttpStatus.OK);
	}
}
