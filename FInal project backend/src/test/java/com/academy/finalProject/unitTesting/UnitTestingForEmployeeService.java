package com.academy.finalProject.unitTesting;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import com.academy.finalProject.DTO.EmployeeDTO;
import com.academy.finalProject.entity.Batch;
import com.academy.finalProject.entity.Course;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.repository.EmployeeRepository;
import com.academy.finalProject.service.EmployeeFactory;
import com.academy.finalProject.service.EmployeeService;

public class UnitTestingForEmployeeService {

	private EmployeeService employeeService;
	
	private EmployeeRepository employeeRepository;
	
	private EmployeeFactory employeeFactory;
	
	@BeforeEach
	public void setup() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		employeeFactory = Mockito.mock(EmployeeFactory.class);
		employeeService = new EmployeeService(employeeRepository,employeeFactory);
	}
	
	@Test
	public void testAddAdmin(){
		EmployeeDTO employeeDTO = new EmployeeDTO("somoy","bjit1234","somoy@gmail.com","+8801243433523","ROLE_ADMIN");
		Employee employee = new Employee(Long.parseLong("7"),"somoy","bjit1234","somoy@gmail.com","+8801243433523","ROLE_ADMIN");
		when(employeeFactory.addEmployeeFactory(employeeDTO)).thenReturn(employee);
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee,employeeService.addEmployeeService(employeeDTO));
	}
	
	@Test
	public void testAddTrainee(){
		EmployeeDTO employeeDTO = new EmployeeDTO("somoy","bjit1234","somoy@gmail.com","+8801243433523","ROLE_TRAINEE","Fresh_Batch_06");
		Batch batch = new Batch(Long.parseLong("1"), "Fresh_Batch_06");
		Trainee trainee = new Trainee(Long.parseLong("7"),"somoy","bjit1234","somoy@gmail.com","+8801243433523","ROLE_TRAINEE",batch);
		when(employeeFactory.addEmployeeFactory(employeeDTO)).thenReturn(trainee);
		when(employeeRepository.save(trainee)).thenReturn(trainee);
		assertEquals(trainee,employeeService.addEmployeeService(employeeDTO));
	}
	
	@Test
	public void testUpdateTrainee(){
		EmployeeDTO employeeDTO = new EmployeeDTO(Long.parseLong("7"),"somoy","bjit1234","somoy@gmail.com","+8801243433523","ROLE_TRAINEE","Fresh_Batch_06");
		Batch batch = new Batch(Long.parseLong("1"), "Fresh_Batch_06");
		Trainee trainee = new Trainee(Long.parseLong("7"),"somoy","bjit1234","somoy@gmail.com","+8801243433523","ROLE_TRAINEE",batch);
		when(employeeFactory.updateEmployeeFactory(employeeDTO)).thenReturn(trainee);
		when(employeeRepository.save(trainee)).thenReturn(trainee);
		assertEquals(trainee,employeeService.updateEmployeeService(employeeDTO));
	}
	
	public Course getCourse(Batch batch) {
		Date startDate = Date.valueOf("2022-06-01");
		Date endDate = Date.valueOf("2022-08-10");
		Course course = new Course(Long.parseLong("1"),"Java_06",startDate,endDate,"ON-GOING",batch.getName(),batch);
		return course;
	}
}
