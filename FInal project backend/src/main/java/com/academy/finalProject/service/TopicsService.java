package com.academy.finalProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.entity.Topics;
import com.academy.finalProject.repository.CourseRepository;
import com.academy.finalProject.repository.TopicsRepository;

@Service
public class TopicsService {

	@Autowired
	TopicsRepository topicsRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	public Topics getTopicsByNameForCourse(String name,Long course_id) {
		Optional<Topics> optionalTopics = topicsRepository.findByNameAndCourseId(name, course_id);
		if(optionalTopics.isPresent()) {
			return optionalTopics.get();
		}
		else {
			Topics topics = new Topics();
			topics.setName(name);
			topics.setCourse(courseRepository.findById(course_id).get());
			return topicsRepository.save(topics);
		}
	}
	
	public void updateTopicsService(Topics topics) {
		topicsRepository.save(topics);
	}
	
	public Topics getTopicsByNameAndCourseId(String name,Long course_id) {
		Optional<Topics> optionalTopics = topicsRepository.findByNameAndCourseId(name,course_id);
		if(optionalTopics.isPresent()){
			return optionalTopics.get();
		}
		return null;
	}
	
	public List<Object> getAllTopicsOfCourse(Long course_id){
		if(!topicsRepository.findAllTopicNameOfCourse(course_id).isEmpty()) {
			return topicsRepository.findAllTopicNameOfCourse(course_id);
		}
		return null;
	}
	
	public void deleteTopics(Topics topics) {
		topicsRepository.delete(topics);
	}
}
