package com.academy.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.finalProject.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByName(String name);
}
