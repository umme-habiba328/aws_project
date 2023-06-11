package com.academy.finalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.finalProject.DTO.BatchDTO;
import com.academy.finalProject.DTO.TraineeDTO;
import com.academy.finalProject.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long> {

	@Query(value = "SELECT b.id as Id,b.name as Name from batch b where name = ?1",nativeQuery = true)
	public Optional<BatchDTO> findDTOByName(String name);

	public Batch findByName(String name);
	
	@Query(value = "SELECT e.id as Id, e.name as Name,e.email as Email,t.course_id as CourseId from trainee t join employee e "
			+ "on t.id = e.id where batch_id = ?1",nativeQuery=true)
	public List<TraineeDTO> findAllTraineesByBatchId(Long id);
	
}
