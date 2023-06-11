package com.academy.finalProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.finalProject.entity.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {

	public List<Answers> findByTakenQuizQuizId(Long quizId);

}
