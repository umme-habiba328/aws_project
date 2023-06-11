package com.academy.finalProject.controller;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.academy.finalProject.DTO.UpdateCourseDTO;
import com.academy.finalProject.entity.Course;
import com.academy.finalProject.exception.TimeDifferenceException;
import com.academy.finalProject.exception.WrongCourseStatusException;
import com.academy.finalProject.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@PostMapping("")
	public ResponseEntity<Object> addCourse(@Valid @RequestBody Course course, BindingResult bindingResult) throws MethodArgumentNotValidException, TimeDifferenceException, WrongCourseStatusException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		courseService.addCourseService(course);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateCourse(@Valid @RequestBody UpdateCourseDTO updateCourseDTO,BindingResult bindingResult) throws MethodArgumentNotValidException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		courseService.updateCourseDTOService(updateCourseDTO);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getCourse(@RequestParam String title){
		return new ResponseEntity<>(courseService.getCourseByTitleService(title),HttpStatus.OK);
	}
	
	@GetMapping("/allTopics")
	public ResponseEntity<Object> getAllTopics(@RequestParam Long id){
		return new ResponseEntity<>(courseService.getAllTopicsOfCourse(id), HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Object> deleteCourse(@RequestParam Long id) throws IllegalArgumentException {
		courseService.deleteCourseService(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/deleteTopicsFromCourse")
	public ResponseEntity<Object> deleteTopicsFromCourse(@RequestParam String name, @RequestParam Long course_id){
		courseService.deleteTopicsFromCourse(name,course_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/allCourse")
	public ResponseEntity<Object> getAllCourse(@RequestParam String status){
		if(status == null) {
			return new ResponseEntity<>(courseService.getAllCourse(),HttpStatus.OK);
		}
		return new ResponseEntity<>(courseService.getAllCourseBystatus(status),HttpStatus.OK);
	}
}
