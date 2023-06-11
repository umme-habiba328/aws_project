package com.academy.finalProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.QuestionDTO;
import com.academy.finalProject.entity.Answers;
import com.academy.finalProject.entity.TakenQuiz;
import com.academy.finalProject.repository.AnswersRepository;
import com.academy.finalProject.repository.OptionsRepository;

@Service
public class AnswerService {
	
	@Autowired
	QuestionsService questionsService;
	
	@Autowired
	OptionsRepository optionsRepository;
	
	@Autowired
	AnswersRepository answersRepository;

	public List<Answers> addAnswerForQuiz(TakenQuiz takenQuiz, List<QuestionDTO> questions) {
		
		return questions.stream()
				.map(question -> {Answers answer = new Answers();
								  answer.setAnswerOption(question.getAnswer());
								  answer.setQuestion(questionsService.getQuestionById(question.getId()));
								  answer.setTakenQuiz(takenQuiz);
								  answer.setObtainedMarks(0);
								  answer = answersRepository.save(answer);
								  return answer;
		}).collect(Collectors.toList());
	}
	
	public List<Answers> getAnswersForQuiz(Long quizId){
		return answersRepository.findByTakenQuizQuizId(quizId);
	}
}
