package com.academy.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.EmployeeDTO;
import com.academy.finalProject.DTO.EmployeeUpdateDTO;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.entity.Trainer;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.repository.EmployeeRepository;
import com.academy.finalProject.repository.TraineeRepository;
import com.academy.finalProject.repository.TrainerRepository;

@Service
public class EmployeeService {
	
	public EmployeeService() {
		
	}
	
	public EmployeeService(EmployeeRepository employeeRepository,EmployeeFactory employeeFactory) {
		this.employeeFactory = employeeFactory;
		this.employeeRepository = employeeRepository;
	}
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeFactory employeeFactory;
	
	public Employee addEmployeeService(EmployeeDTO employeeDTO) {
		Employee employee = employeeFactory.addEmployeeFactory(employeeDTO);
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	public Employee updateEmployeeService(EmployeeDTO employeeUpdateDTO) {
		Employee employee = employeeFactory.updateEmployeeFactory(employeeUpdateDTO);
		return employeeRepository.save(employee);
	}

	public void deleteTraineeService(Long id) throws IllegalArgumentException{
		employeeRepository.deleteById(id);
	}
}
