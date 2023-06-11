package com.academy.finalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.finalProject.DTO.GetQuizPerformanceDTO;
import com.academy.finalProject.DTO.GetTakenQuizDTO;
import com.academy.finalProject.entity.TakenQuiz;

public interface TakenQuizRepository extends JpaRepository<TakenQuiz, Long> {

	@Query(value = "select takenquiz.id as id, takenquiz.date as date, takenquiz.obtained_marks as "
			+ "obtainedMarks, quiz.topic as topic, quiz.total_marks as totalMarks from "
			+ "taken_quiz takenquiz left outer join employee employee on takenquiz.trainee_id=employee.id "
			+ "left outer join quiz quiz on takenquiz.quiz_id=quiz.id where employee.id=?1 and quiz.id=?2",nativeQuery = true)
	public Optional<GetTakenQuizDTO> findByTraineeIdAndQuizId(Long traineeId,Long quizId);

	@Query(value = "select takenquiz.id as id, takenquiz.date as date, takenquiz.obtained_marks as "
			+ "obtainedMarks, quiz.topic as topic, quiz.total_marks as totalMarks from "
			+ "taken_quiz takenquiz left outer join employee employee on takenquiz.trainee_id=employee.id "
			+ "left outer join quiz quiz on takenquiz.quiz_id=quiz.id where employee.id=?1",nativeQuery = true)
	public List<GetTakenQuizDTO> findByTraineeId(Long id);
	
	@Query(value = "select takenquiz.date as date, takenquiz.obtained_marks as "
			+ "marks, quiz.topic as topic, course.title as courseName,trainee.name as traineeName  from "
			+ "taken_quiz takenquiz left outer join employee employee on takenquiz.trainee_id=employee.id "
			+ "join quiz quiz on takenquiz.quiz_id=quiz.id join course  course on "
			+ "quiz.course_id=course.id join employee trainee on trainee.id= takenquiz.trainee_id where quiz.id=?1",nativeQuery = true)
	public List<GetQuizPerformanceDTO> getAllTakenQuizById(Long id);
}
