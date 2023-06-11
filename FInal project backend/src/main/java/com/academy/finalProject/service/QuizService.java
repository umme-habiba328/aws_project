package com.academy.finalProject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.AddQuizDTO;
import com.academy.finalProject.DTO.QuizDTO;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Quiz;
import com.academy.finalProject.entity.Trainee;
import com.academy.finalProject.repository.CourseRepository;
import com.academy.finalProject.repository.EmployeeRepository;
import com.academy.finalProject.repository.QuizRepository;
import com.academy.finalProject.repository.TakenQuizRepository;
import com.academy.finalProject.repository.TraineeRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionsService questionsService;

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	TakenQuizRepository takenQuizRepository;
	
	public void addQuizService(AddQuizDTO addQuizDTO) {
		Quiz quiz = new Quiz();
		quiz.setCourse(courseRepository.findByTitle(addQuizDTO.getCourse()));
		quiz.setStartTime(addQuizDTO.getStartTime());
		LocalDateTime date = LocalDateTime.now();
		if(date.compareTo(addQuizDTO.getStartTime()) < 0) {
			quiz.setStatus("SCHEDULED");
		}
		else if(date.compareTo(addQuizDTO.getStartTime()) > 0) {
			quiz.setStatus("ON-GOING");
		}
		quiz.setTopic(addQuizDTO.getTopic());
		quiz.setTrainer(employeeRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
		quiz = quizRepository.save(quiz);
		quiz.setQuestions( questionsService.addQuestionsService(addQuizDTO.getQuestions(), quiz));
		quiz.setTotalMarks();
		quizRepository.save(quiz);
	}

	public Quiz getQuizById(Long id) {
		return quizRepository.getById(id);
	}
	
	public List<QuizDTO> getScheduledQuizForCourse(){
		Trainee trainee = traineeRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Long courseId = trainee.getCourse().getId();
		return quizRepository.findByStatusCourseId(courseId);
	}
	
	public List<QuizDTO> getAllScheduledQuizForTrainer() throws Exception{
		Employee trainer = employeeRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		if(trainer.getRole().equals("ROLE_TRAINER")) {
			return quizRepository.findQuizzesForTrainer(trainer.getId());
		}else {
			throw new Exception("You are not trainer");
		}
	}
	
	public Quiz getQuizForTrainee(Long quizId) throws Exception {
		Employee trainee = traineeRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		if(!takenQuizRepository.findByTraineeIdAndQuizId(trainee.getId(), quizId).isPresent()) {
			QuizDTO quizDTO = quizRepository.findByQuizId(quizId).get();
			Trainee traineeCheckCourse = traineeRepository.getOne(trainee.getId());
			
			LocalDateTime date = LocalDateTime.now();
			if(quizDTO.getStartTime().compareTo(date) <= 0 && traineeCheckCourse.getCourse().getTitle().equals(quizDTO.getCourseName())) {
				Quiz quiz = new Quiz();
				quiz.setQuestions(questionsService.getQuestionByQuizId(quizId));
				quiz.setId(quizId);
				quiz.setCourse(courseRepository.findByTitle(quizDTO.getCourseName()));
				quiz.setStartTime(quizDTO.getStartTime());
				quiz.setStatus(quizDTO.getStatus());
				quiz.setTopic(quizDTO.getTopic());
				return quiz;
			}
			else {
				throw new Exception("Quiz is closed");
			}
		}
		return null;
	}
}
