package com.academy.finalProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.finalProject.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	public Course findByTitle(String title);

	public List<Course> findByStatus(String status);
}
