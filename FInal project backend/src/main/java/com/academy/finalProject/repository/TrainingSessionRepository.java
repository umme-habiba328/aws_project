package com.academy.finalProject.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.finalProject.DTO.TrainingSessionDTO;
import com.academy.finalProject.entity.TrainingSession;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long>{

	public Optional<TrainingSession> findByTrainerIdAndCourseId(Long trainerId,Long courseId);
	
	public List<TrainingSession> findByTrainerIdAndDateAndCourseIdOrderByStartTime(Long trainerId,Date date,Long courseId);
	
	@Query(value = "select trainingse0_.id as id, trainingse0_.course_id as course, trainingse0_.date as date, "
			+ "trainingse0_.end_time as endTime, trainingse0_.start_time as startTime, "
			+ "trainingse0_.status as status, trainingse0_.topic as topic, "
			+ "trainingse0_.trainer_id as trainer from training_session trainingse0_ left outer "
			+ "join employee employee1_ on trainingse0_.trainer_id=employee1_.id left outer join course "
			+ "course2_ on trainingse0_.course_id=course2_.id where employee1_.id=?1 and trainingse0_.date=?2 "
			+ "and course2_.id=?3 and trainingse0_.id not ?4 order by trainingse0_.start_time asc",nativeQuery = true)
	public List<TrainingSession> findByTrainerIdAndDateAndCourseIdAndNotIdOrderByStartTime(Long trainerId,Date date,Long courseId,Long id);
	
	@Query(value = "select trainingsession.id as id, course.title as courseTitle, trainingsession.date as"
			+ " date, trainingsession.end_time as endTime, trainingsession.start_time as startTime,"
			+ "trainingsession.status as status, trainingsession.topic as topic, employee.name "
			+ "as trainerName from training_session trainingsession left outer join employee "
			+ "on trainingsession.trainer_id=employee.id left outer join course on "
			+ "trainingsession.course_id=course.id where trainingsession.date=?1 and course.title = ?2 order by trainingsession.start_time asc", nativeQuery = true)
	public List<TrainingSessionDTO> findByCourseNameAndDate(Date date,String courseId);
	
	@Query(value = "select trainingsession.id as id, course.title as courseTitle, trainingsession.date as"
			+ " date, trainingsession.end_time as endTime, trainingsession.start_time as startTime,"
			+ "trainingsession.status as status, trainingsession.topic as topic, employee.name "
			+ "as trainerName from training_session trainingsession left outer join employee "
			+ "on trainingsession.trainer_id=employee.id left outer join course on "
			+ "trainingsession.course_id=course.id where course.title = ?1 order by trainingsession.date asc", nativeQuery = true)
	public List<TrainingSessionDTO> findByCourseName(String courseName);
	
	@Query(value = "select trainingsession.id as id, course.title as courseTitle, trainingsession.date as"
			+ " date, trainingsession.end_time as endTime, trainingsession.start_time as startTime,"
			+ "trainingsession.status as status, trainingsession.topic as topic, employee.name "
			+ "as trainerName from training_session trainingsession left outer join employee "
			+ "on trainingsession.trainer_id=employee.id left outer join course on "
			+ "trainingsession.course_id=course.id where trainingsession.date=?1 order by trainingsession.start_time", nativeQuery = true)
	public List<TrainingSessionDTO> findByDateDTO(Date date);
	
	public List<TrainingSession> findByDateOrderByStartTime(Date date);
	
	@Query(value = "select trainingsession.id as id, course.title as courseTitle, trainingsession.date as date,"
			+ "trainingsession.end_time as endTime, trainingsession.start_time as startTime,"
			+ "trainingsession.status as status, trainingsession.topic as topic, employee.name "
			+ "as trainerName from training_session trainingsession left outer join employee "
			+ "on trainingsession.trainer_id=employee.id left outer join course on "
			+ "trainingsession.course_id=course.id where trainingsession.id=?1", nativeQuery = true)
	public TrainingSessionDTO searchById(Long id);
}
