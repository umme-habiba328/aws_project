package com.academy.finalProject.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.EmployeeDTO;
import com.academy.finalProject.DTO.EmployeeUpdateDTO;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.entity.Trainer;
import com.academy.finalProject.exception.CredentialsAlreadyExistsException;
import com.academy.finalProject.repository.BatchRepository;

@Service
public class EmployeeFactory {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	BatchRepository batchRepository;
	
	public Employee addEmployeeFactory(EmployeeDTO employeeDTO) {
		if(employeeDTO.getRole().equals("ROLE_ADMIN")) {
			Employee employee = new Employee();
			employee.setName(employeeDTO.getName());
			employee.setPassword(employeeDTO.getPassword());
			employee.setEmail(employeeDTO.getEmail());
			employee.setRole(employeeDTO.getRole());
			employee.setContactNumber(employeeDTO.getContactNumber());
			return employee;
		}
		else if(employeeDTO.getRole().equals("ROLE_TRAINER")) {
			Trainer trainer = new Trainer(employeeDTO.getName(),employeeDTO.getPassword(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getRole());
			trainer.setCourses(employeeDTO.getCourses().stream()
					.map(title -> courseService.getCourseByTitleService(title))
					.collect(Collectors.toList()));
			return trainer;
		}
		else if(employeeDTO.getRole().equals("ROLE_TRAINEE")) {
			Trainee trainee = new Trainee(employeeDTO.getName(),employeeDTO.getPassword(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getRole());
			return trainee;
		}
		return null;
	}
	
	public Employee updateEmployeeFactory(EmployeeDTO employeeDTO) {
		if(employeeDTO.getRole().equals("ROLE_ADMIN")) {
			Employee employee = new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getPassword(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getRole());
			return employee;
		}
		else if(employeeDTO.getRole().equals("ROLE_TRAINER")) {
			Trainer trainer = new Trainer(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getPassword(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getRole());
			trainer.setCourses(employeeDTO.getCourses().stream()
					.map(title -> courseService.getCourseByTitleService(title))
					.collect(Collectors.toList()));
			return trainer;
		}
		else if(employeeDTO.getRole().equals("ROLE_TRAINEE")) {
			Trainee trainee = new Trainee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getPassword(),employeeDTO.getEmail(),employeeDTO.getContactNumber(),employeeDTO.getRole());
			trainee.setBatch(batchRepository.findByName(employeeDTO.getBatch()));
			trainee.setCourse(courseService.getCourseByTitleService(employeeDTO.getCourse()));
			return trainee;
		}
		return null;
	}
}
