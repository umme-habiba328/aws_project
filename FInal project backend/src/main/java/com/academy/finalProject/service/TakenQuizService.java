package com.academy.finalProject.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.AddQuizDTO;
import com.academy.finalProject.DTO.GetQuizPerformanceDTO;
import com.academy.finalProject.DTO.GetTakenQuizDTO;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.TakenQuiz;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.repository.TakenQuizRepository;

@Service
public class TakenQuizService {
	
	@Autowired
	TakenQuizRepository takenQuizRepository;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	EmployeeService traineeService;

	public void addTakenQuiz(AddQuizDTO addQuizDTO) {
		TakenQuiz takenQuiz = new TakenQuiz();
		takenQuiz.setDate(addQuizDTO.getStartTime());
		takenQuiz.setQuiz(quizService.getQuizById(addQuizDTO.getId()));
		takenQuiz.setTrainee(traineeService.getEmployeeByName(SecurityContextHolder.getContext().getAuthentication().getName()));
		takenQuiz = takenQuizRepository.save(takenQuiz);
		takenQuiz.setAnswers(answerService.addAnswerForQuiz(takenQuiz,addQuizDTO.getQuestions()));
		takenQuiz.setObtainedMarks(0);
		takenQuizRepository.save(takenQuiz);
	}

	public boolean traineeHasTakenQuiz(Long quizId) {
		Employee trainee = traineeService.getEmployeeByName(SecurityContextHolder.getContext().getAuthentication().getName());
		if(takenQuizRepository.findByTraineeIdAndQuizId(trainee.getId(), quizId).isPresent()) {
			return true;
		}
		else {
			return false;
		}
	}

	public GetTakenQuizDTO getTakenQuizForTrainee(Long quizId) throws Exception {
		Employee trainee = traineeService.getEmployeeByName(SecurityContextHolder.getContext().getAuthentication().getName());
		Optional<GetTakenQuizDTO> takenQuizOptional = takenQuizRepository.findByTraineeIdAndQuizId(trainee.getId(), quizId);
		if(takenQuizOptional.isPresent()) {
			System.out.println("Date of quiz: "+takenQuizOptional.get().getDate());
			return takenQuizOptional.get();
		}
		else {
			throw new Exception("Quiz is not for this trainee");
		}
	}
	
	public List<GetTakenQuizDTO> getAllTakenQuizForTrainee(Long quizId) throws Exception {
		Employee trainee = traineeService.getEmployeeByName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<GetTakenQuizDTO> takenQuizOptional = takenQuizRepository.findByTraineeId(trainee.getId());
		if(!takenQuizOptional.isEmpty()) {
			return takenQuizOptional;
		}
		else {
			throw new Exception("No taken quizzes for this trainee");
		}
	}
	
	public List<GetQuizPerformanceDTO> getTakenQuizPerformaneService(Long quizId){
		return takenQuizRepository.getAllTakenQuizById(quizId);
	}
}
