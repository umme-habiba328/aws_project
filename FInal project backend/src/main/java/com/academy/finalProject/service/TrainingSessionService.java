package com.academy.finalProject.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.AddTrainingSessionDTO;
import com.academy.finalProject.DTO.TrainingSessionDTO;
import com.academy.finalProject.entity.Course;
import com.academy.finalProject.entity.Employee;
import com.academy.finalProject.entity.Topics;
import com.academy.finalProject.entity.TrainingSession;
import com.academy.finalProject.repository.TrainingSessionRepository;

@Service
public class TrainingSessionService {

	@Autowired
	TrainingSessionRepository trainingSessionRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	TrainerService trainerService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	TopicsService topicsService;
	
	public void addTrainingSessionService(AddTrainingSessionDTO addTrainingSessionDTO) throws Exception {
		TrainingSession trainingSession = new TrainingSession();
		trainingSession.setStatus(addTrainingSessionDTO.getStatus());
		trainingSession.setDate(Date.valueOf(addTrainingSessionDTO.getDate()));
		trainingSession.setStartTime(Time.valueOf(addTrainingSessionDTO.getStartTime()+ ":00"));
		trainingSession.setEndTime(Time.valueOf(addTrainingSessionDTO.getEndTime()+ ":00"));
		compareTime(trainingSession.getStartTime(),trainingSession.getEndTime());
		compareDateWithStatus(trainingSession.getStatus(),trainingSession.getDate());
		
		Course course = courseService.getCourseByTitleService(addTrainingSessionDTO.getCourse());
		Employee trainer = employeeService.getEmployeeByName(addTrainingSessionDTO.getTrainer());
		
		if(trainer.getRole().equals("ROLE_TRAINER") && trainerService.containsCourseId(trainer.getId(), course.getId()) && 
				!alreadyHasSession(trainer.getId(),course.getId(),trainingSession.getStartTime(),trainingSession.getEndTime(),trainingSession.getDate())) {
			trainingSession.setCourse(course);
			trainingSession.setTrainer(trainer);
		}
		else {
			throw new Exception("Trainer is not for this course");
		}
		
		Topics topic = topicsService.getTopicsByNameForCourse(addTrainingSessionDTO.getTopic(), course.getId());
		trainingSession.setTopic(topic.getName());
		trainingSessionRepository.save(trainingSession);
	}
	
	public boolean alreadyHasSession(Long trainerId, Long courseId, Time startTime,Time endTime, Date date) throws Exception {
		List<TrainingSession> trainingSession = trainingSessionRepository.findByTrainerIdAndDateAndCourseIdOrderByStartTime(trainerId,date, courseId);
		int flag = 0;
		for(TrainingSession t:trainingSession) {
			System.out.println(t);
			if(t.getStartTime().compareTo(endTime) >= 0 || t.getEndTime().compareTo(startTime) <= 0) {}
			else { flag = 1; }
		}
		if(flag == 0) {
			return false;
		}
		else { throw new Exception("Trainer already has session"); }
	}
	
	public void compareTime(Time startTime,Time endTime) throws Exception {
		if(startTime.after(endTime)) {
			throw new Exception("End time should be greater than start time");
		}
	}
	
	public void compareDateWithStatus(String status,Date date) throws Exception {
		Date d = Date.valueOf(LocalDate.now());
		
		if(status.equals("ON-GOING") && date.equals(d)) {}
		else if(status.equals("SCHEDULED") && date.after(d)) {}
		else if(status.equals("ENDED") && date.before(d)) {}
		else {
			throw new Exception("Status is wrong");
		}
	}
	
	public void deleteById(Long id) {
		trainingSessionRepository.deleteById(id);
	}

	public List<TrainingSessionDTO> getTrainingSessionForCourse(String coursename,Date date){
		return trainingSessionRepository.findByCourseNameAndDate(date, coursename);
	}
	
	public List<TrainingSessionDTO> getTrainingSessionByDate(Date date){
		return trainingSessionRepository.findByDateDTO(date);
	}
	
	public TrainingSessionDTO getById(Long id) {
		// TODO Auto-generated method stub
		return trainingSessionRepository.searchById(id);
	}

	public void updateTrainingSession(AddTrainingSessionDTO trainingSessionDTO) throws Exception {
		TrainingSession trainingSession = new TrainingSession();
		trainingSession.setId(trainingSessionDTO.getId());
		trainingSession.setStatus(trainingSessionDTO.getStatus());
		trainingSession.setDate(Date.valueOf(trainingSessionDTO.getDate()));
		trainingSession.setStartTime(Time.valueOf(trainingSessionDTO.getStartTime().substring(0, 5) + ":00"));
		trainingSession.setEndTime(Time.valueOf(trainingSessionDTO.getEndTime().substring(0, 5) + ":00"));
		compareTime(trainingSession.getStartTime(),trainingSession.getEndTime());
		compareDateWithStatus(trainingSession.getStatus(),trainingSession.getDate());
		
		Course course = courseService.getCourseByTitleService(trainingSessionDTO.getCourse());
		Employee trainer = employeeService.getEmployeeByName(trainingSessionDTO.getTrainer());
		
		if(trainer.getRole().equals("ROLE_TRAINER") && trainerService.containsCourseId(trainer.getId(), course.getId())) {
		//	&&	alreadyHasSessionUpdate(course.getId(),trainer.getId(),trainingSession.getId(),trainingSession.getStartTime(),trainingSession.getEndTime(),trainingSession.getDate())) {
			trainingSession.setCourse(course);
			trainingSession.setTrainer(trainer);
		}
		else {
			throw new Exception("Trainer is not for this course");
		}
		
		Topics topic = topicsService.getTopicsByNameForCourse(trainingSessionDTO.getTopic(), course.getId());
		trainingSession.setTopic(topic.getName());
		trainingSessionRepository.save(trainingSession);
	}
	
	public boolean alreadyHasSessionUpdate(Long id,Long trainerId, Long courseId, Time startTime,Time endTime, Date date) throws Exception {
		List<TrainingSession> trainingSession = trainingSessionRepository.findByTrainerIdAndDateAndCourseIdAndNotIdOrderByStartTime(trainerId,date, courseId,id);
		int flag = 0;
		for(TrainingSession t:trainingSession) {
			System.out.println(t);
			if(t.getStartTime().compareTo(endTime) >= 0 || t.getEndTime().compareTo(startTime) <= 0) {}
			else { flag = 1; }
		}
		if(flag == 0) {
			return false;
		}
		else { throw new Exception("Trainer already has session"); }
	}
	
	public void updateTrainingSessionStatusService() {
		Date date = Date.valueOf(LocalDate.now());
		Time time = Time.valueOf(LocalTime.now());
		trainingSessionRepository.findByDateOrderByStartTime(date)
		.stream().forEach(trainingSession -> {if(trainingSession.getStartTime().compareTo(time) == 0) {
										if(trainingSession.getStatus().equals("SCHEDULED")) { trainingSession.setStatus("ON-GOING"); trainingSessionRepository.save(trainingSession);}
									}else if(trainingSession.getEndTime().compareTo(time) < 0) {
										if(trainingSession.getStatus().equals("ON-GOING")) {trainingSession.setStatus("ENDED");trainingSessionRepository.save(trainingSession);}
									}
						});
	}

	public List<TrainingSessionDTO> getTrainingSessionByCourse(String courseName) {
		// TODO Auto-generated method stub
		return trainingSessionRepository.findByCourseName(courseName);
	}
}
