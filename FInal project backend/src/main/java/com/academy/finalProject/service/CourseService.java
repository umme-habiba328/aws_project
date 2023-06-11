package com.academy.finalProject.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.TraineeDTO;
import com.academy.finalProject.DTO.UpdateCourseDTO;
import com.academy.finalProject.entity.Course;
import com.academy.finalProject.entity.Topics;
import com.academy.finalProject.exception.TimeDifferenceException;
import com.academy.finalProject.exception.WrongCourseStatusException;
import com.academy.finalProject.repository.BatchRepository;
import com.academy.finalProject.repository.CourseRepository;
import com.academy.finalProject.repository.TraineeRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	TraineeRepository traineeRepository;

	@Autowired
	BatchService batchService;
	
	@Autowired
	TopicsService topicsService;
	
	public void addCourseService(Course course) throws TimeDifferenceException, WrongCourseStatusException {
		if(course.getEndDate().compareTo(course.getStartDate()) > 0) {
			
			Date d = Date.valueOf(LocalDate.now());
			if(course.getStatus().equals("ON-GOING") && d.after(course.getStartDate()) && d.before(course.getEndDate())) {
				courseRepository.save(course);
			}
			else if(course.getStatus().equals("UP-COMING") && d.before(course.getStartDate())) {
				courseRepository.save(course);
			}
			else if(course.getStatus().equals("ENDED") && d.after(course.getEndDate())) {
				courseRepository.save(course);
			}
			else {
				throw new WrongCourseStatusException("Course status is wrong");
			}
		}
		else {
			throw new TimeDifferenceException("End time should be greater");
		}
	}

	public Course updateCourseService(Course course) {
		return courseRepository.save(course);
	}

	public void deleteCourseService(Long id) {
		topicsService.getAllTopicsOfCourse(id).stream().map(topic -> topicsService.getTopicsByNameAndCourseId(topic.toString(), id))
														.forEach(topic -> topicsService.deleteTopics(topic));	
		courseRepository.deleteById(id);
	}
	
	public Course getCourseService(Long id) {
		return courseRepository.findById(id).get();
	}
	
	public Course getCourseByTitleService(String title) {
		Course course = courseRepository.findByTitle(title);
		return course;
	}

	public List<Object> getAllTopicsOfCourse(Long course_id){
		return topicsService.getAllTopicsOfCourse(course_id);
	}
	
	public void updateCourseDTOService(UpdateCourseDTO updateCourseDTO) {
		Course course = new Course(updateCourseDTO.getId(),updateCourseDTO.getTitle(),updateCourseDTO.getStartDate(),updateCourseDTO.getEndDate(),
				updateCourseDTO.getStatus(),updateCourseDTO.getBatch(),null);
		course.setBatch(batchService.getBatchService(updateCourseDTO.getBatch()));
		course.setBatchName(updateCourseDTO.getBatch());
		updateCourseDTO.getTopics().stream().map(name -> topicsService.getTopicsByNameForCourse(name, course.getId()))
											.map(topic -> {topic.setCourse(course); return topic;}).forEach(topic -> topicsService.updateTopicsService(topic));
		
		courseRepository.save(course);
	}

	public void deleteTopicsFromCourse(String name,Long course_id) {
		Topics topics = topicsService.getTopicsByNameAndCourseId(name, course_id);
		topics.setCourse(null);
		topicsService.deleteTopics(topics);
	}
	
	public void updateCourseStatusService() {
		Date date = Date.valueOf(LocalDate.now());
		
		courseRepository.findAll()
		.stream().forEach(course -> {if(course.getStartDate().compareTo(date) <= 0 && course.getEndDate().compareTo(date) > 0) {
										if(course.getStatus().equals("UP-COMING")) { course.setStatus("ON-GOING"); courseRepository.save(course);}
									}else if(course.getEndDate().compareTo(date) < 0) {
										if(course.getStatus().equals("ON-GOING")) {course.setStatus("ENDED");courseRepository.save(course);}
									}
						});
	}

	public List<TraineeDTO> getAllTraineesOfCourse(Long courseId,Long batchId) {
		return traineeRepository.findAllTraineesByCourseIdAndBatchId(courseId,batchId);
	}

	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	public List<Course> getAllCourseBystatus(String status) {
		// TODO Auto-generated method stub
		return courseRepository.findByStatus(status);
	}
}
