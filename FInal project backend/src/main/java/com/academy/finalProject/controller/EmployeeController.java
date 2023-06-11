package com.academy.finalProject.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.DTO.EmployeeDTO;
import com.academy.finalProject.DTO.EmployeeUpdateDTO;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.service.EmployeeFactory;
import com.academy.finalProject.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeFactory employeeFactory;

	@GetMapping("/update")
	public ResponseEntity<Object> getEmployee(@RequestParam String name){
		return new ResponseEntity<>(employeeService.getEmployeeByName(name),HttpStatus.OK);
	}
	
	@PostMapping("")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> addTrainer(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) throws CredentialsAlreadyExistsException, MethodArgumentNotValidException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		employeeService.addEmployeeService(employeeDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDTO employeeUpdateDTO,BindingResult bindingResult) throws CredentialsAlreadyExistsException, MethodArgumentNotValidException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		employeeService.updateEmployeeService(employeeUpdateDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Object> deleteEmployee(@RequestParam Long id) throws IllegalArgumentException {
		employeeService.deleteTraineeService(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
