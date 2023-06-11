package com.academy.finalProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.finalProject.DTO.GetQuestionDTO;
import com.academy.finalProject.entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

	@Query(value = "select questions0_.id as id, questions0_.marks as "
			+ "marks, questions0_.question_description as questionDescription from "
			+ "questions questions0_ where questions0_.quiz_id=?1",nativeQuery = true)
	public List<GetQuestionDTO> findDTOByQuizId(Long id);
}
