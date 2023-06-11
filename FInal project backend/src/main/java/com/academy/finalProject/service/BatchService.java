package com.academy.finalProject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.finalProject.DTO.BatchDTO;
import com.academy.finalProject.DTO.TraineeDTO;
import com.academy.finalProject.entity.Batch;
import com.academy.finalProject.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	BatchRepository batchRepository;
	
	public void addBatchService(Batch batch) {
		batchRepository.save(batch);
	}
	
	public Batch getBatchService(String name) {
		return batchRepository.findByName(name);
	}

	public BatchDTO getBatchDTOService(String name) {
		System.out.println(batchRepository.findDTOByName(name));
		return batchRepository.findDTOByName(name).get();
	}
	
	public void updateBatchService(Batch batch) {
		batchRepository.save(batch);
	}
	
	public void deleteBatchService(Long id) {
		batchRepository.deleteById(id);
	}

	public List<TraineeDTO> getAllTraineesService(Long id) {
		return batchRepository.findAllTraineesByBatchId(id);
	}
	
	public Batch getBatchById(Long id) {
		return batchRepository.findById(id).get();
	}
}
