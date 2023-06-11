package com.academy.finalProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.DTO.AddQuizDTO;
import com.academy.finalProject.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("")
	public ResponseEntity<Object> addQuiz(@Valid @RequestBody AddQuizDTO addQuizDTO, BindingResult bindingResult) throws MethodArgumentNotValidException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		quizService.addQuizService(addQuizDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/allScheduledQuizForCourse")
	public ResponseEntity<Object> getAllQuizForCourse(){
		return new ResponseEntity<>(quizService.getScheduledQuizForCourse(),HttpStatus.OK);
	}
	
	@GetMapping("/allQuizForTrainer")
	public ResponseEntity<Object> getAllQuizForTrainer() throws Exception{
		return new ResponseEntity<>(quizService.getAllScheduledQuizForTrainer(),HttpStatus.OK);
	}
}
