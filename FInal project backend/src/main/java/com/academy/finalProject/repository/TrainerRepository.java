package com.academy.finalProject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.academy.finalProject.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

	public Trainer findByName(String name);
	
	public Optional<Object> findByIdAndCoursesId(Long id,Long courseId);
}
