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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.DTO.AddQuizDTO;
import com.academy.finalProject.entity.Quiz;
import com.academy.finalProject.entity.TakenQuiz;
import com.academy.finalProject.service.QuizService;
import com.academy.finalProject.service.TakenQuizService;

@RestController
@RequestMapping("/api/takeQuiz")
public class TakenQuizController {
	
	@Autowired
	TakenQuizService takenQuizService;
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("")
	public ResponseEntity<Object> addTakenQuiz(@Valid @RequestBody AddQuizDTO addQuizDTO, BindingResult bindingResult) throws MethodArgumentNotValidException{
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		System.out.println(addQuizDTO);
		takenQuizService.addTakenQuiz(addQuizDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getQuizForTrainee(@RequestParam Long id) throws Exception{
		Quiz quiz = quizService.getQuizForTrainee(id);
		if(quiz != null) {
			return new ResponseEntity<>(quiz,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(takenQuizService.getTakenQuizForTrainee(id),HttpStatus.OK);
		}
	}
	
	@GetMapping("/allTakenQuiz")
	public ResponseEntity<Object> getAllQuizForTrainee(@RequestParam Long id) throws Exception{
			return new ResponseEntity<>(takenQuizService.getAllTakenQuizForTrainee(id),HttpStatus.OK);
	}
	
	@GetMapping("/allTakenQuizPerformance")
	public ResponseEntity<Object> getAllPerformanceOfQuiz(@RequestParam Long quizId){
		return new ResponseEntity<>(takenQuizService.getTakenQuizPerformaneService(quizId),HttpStatus.OK);
	}
}
