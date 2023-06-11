package com.academy.finalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.finalProject.DTO.TraineeDTO;
import com.academy.finalProject.entity.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

	public Optional<Trainee> getByEmail(String email);
	
	public Optional<Trainee> getByContactNumber(String contactNumber);
	
	
	public Optional<Trainee> findByName(String name);
	
	@Query(value="SELECT e.id as Id, e.name as Name, e.email as Email, t.course_id as CourseId "
			+ "from trainee t join employee e on t.id = e.id where e.name = ?1", nativeQuery=true)
	public Optional<TraineeDTO> getDTOByName(String name);
	
	@Query(value = "SELECT e.id as Id, e.name as Name,e.email as Email,t.course_id as CourseId from trainee t join employee e "
			+ "on t.id = e.id where course_id = ?1 and batch_id = ?2",nativeQuery=true)
	public List<TraineeDTO> findAllTraineesByCourseIdAndBatchId(Long courseId,Long BatchId);
}
