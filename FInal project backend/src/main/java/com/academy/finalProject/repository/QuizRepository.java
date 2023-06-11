package com.academy.finalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.finalProject.DTO.QuizDTO;
import com.academy.finalProject.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query(value = "select quiz.id as id, course.title as courseName, quiz.start_time as startTime, "
			+ "quiz.status as status "
			+ "from quiz quiz join course course on quiz.course_id=course.id where "
			+ "course.id=?1 order by quiz.start_time",nativeQuery = true)
	public List<QuizDTO> findByStatusCourseId(Long courseId);
	
	@Query(value = "select quiz.id as id, course.title as courseName, quiz.start_time as startTime, "
			+ "quiz.status as status,quiz.topic as topic "
			+ "from quiz quiz join course course on quiz.course_id=course.id where "
			+ "quiz.id=?1",nativeQuery = true)
	public Optional<QuizDTO> findByQuizId(Long id);
	
	@Query(value = "select quiz.id as id, course.title as courseName, quiz.start_time as startTime, "
			+ "quiz.status as status,quiz.topic as topic "
			+ "from quiz quiz join course course on quiz.course_id=course.id where "
			+ "quiz.trainer_id=?1",nativeQuery = true)
	public List<QuizDTO> findQuizzesForTrainer(Long trainerId);
}
