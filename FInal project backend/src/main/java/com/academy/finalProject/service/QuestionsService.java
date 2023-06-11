package com.academy.finalProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.GetQuestionDTO;
import com.academy.finalProject.DTO.QuestionDTO;
import com.academy.finalProject.entity.Options;
import com.academy.finalProject.entity.Questions;
import com.academy.finalProject.entity.Quiz;
import com.academy.finalProject.repository.OptionsRepository;
import com.academy.finalProject.repository.QuestionsRepository;

@Service
public class QuestionsService {

	@Autowired
	QuestionsRepository questionRepository;
	
	@Autowired
	OptionsRepository optionsRepository;
	
	public List<Questions> addQuestionsService(List<QuestionDTO> questions,Quiz quiz) {
		final boolean isAnswer = true;
		return questions.stream().map(questionDTO -> {
												Questions question = new Questions();
												question.setQuiz(quiz);
												question.setQuestionDescription(questionDTO.getQuestionDescription());
												question.setMarks(questionDTO.getMarks());
												question = questionRepository.save(question);
												List<Options> options = addAllOptions(question,questionDTO.getOptions());
												question.setOptions(options);
												if(isAnswerOption(questionDTO.getAnswer(),question.getId())) {
													question.setAnswerOption(questionDTO.getAnswer());
													return questionRepository.save(question);
												}
												else {
													return null;
												}
												}).collect(Collectors.toList());
	}
	
	public boolean isAnswerOption(String answer,Long questionId) {
		if(optionsRepository.findByOptionDescriptionAndQuestionId(answer,questionId) != null) {
			return true;
		}
		return false;
	}
	
	public Questions getQuestionById(Long id) {
		return questionRepository.findById(id).get();
	}
	
	public List<Options> addAllOptions(Questions question,List<String> options){
		return options.stream().map(option -> {Options o = new Options();
		o.setQuestion(question);
		o.setOptionDescription(option);
		return o;}).map(option -> optionsRepository.save(option)).collect(Collectors.toList());
	}

	public List<Questions> getQuestionByQuizId(Long id) {
		List<GetQuestionDTO> questionDTOList = questionRepository.findDTOByQuizId(id);
		List<Questions> questions = questionDTOList.stream().map(questionDTO -> {Questions question = new Questions();
																question.setId(questionDTO.getId());
																question.setQuestionDescription(questionDTO.getQuestionDescription());
																question.setMarks(questionDTO.getMarks());
																question.setOptions(optionsRepository.findByQuestionId(questionDTO.getId()));
																return question;
		}).collect(Collectors.toList());
		System.out.println("Inside question: " + questions.get(0));
		return questions;
	}
}
