package com.academy.finalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.academy.finalProject.entity.Topics;

public interface TopicsRepository extends JpaRepository<Topics, Long> {

	public Optional<Topics> findByNameAndCourseId(String name, Long course_id);
	
	@Query(value = "SELECT t.name from topics t where t.course_id = ?1",nativeQuery = true)
	public List<Object> findAllTopicNameOfCourse(Long course_id);
}