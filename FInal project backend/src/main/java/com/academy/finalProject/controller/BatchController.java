package com.academy.finalProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.finalProject.DTO.AddTraineesToBatchDTO;
import com.academy.finalProject.DTO.BatchDTO;
import com.academy.finalProject.entity.Batch;
import com.academy.finalProject.service.BatchAdapter;
import com.academy.finalProject.service.BatchService;

@RestController
@RequestMapping("/api/batch")
public class BatchController {
	
	@Autowired
	BatchService batchService;
	
	@Autowired
	BatchAdapter batchAdapter;

	@PostMapping("")
	public ResponseEntity<Object> addBatch(@Valid @RequestBody Batch batch,BindingResult bindingResult) throws MethodArgumentNotValidException {
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		batchService.addBatchService(batch);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("addTrainees")
	public ResponseEntity<Object> addTraineesToBatch(@RequestBody AddTraineesToBatchDTO addTraineeToBatchDTO){
		batchAdapter.addTraineesToBatchAdapter(addTraineeToBatchDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("allTrainees")
	public ResponseEntity<Object> getAllTrainees(@RequestParam Long id){
		return new ResponseEntity<>(batchService.getAllTraineesService(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getBatch(@RequestParam String name) {
		return new ResponseEntity<>(batchService.getBatchDTOService(name),HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateBatch(@Valid @RequestBody Batch batch,BindingResult bindingResult) throws MethodArgumentNotValidException {
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		batchService.updateBatchService(batch);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public void deleteBatch(@RequestParam Long id) {
		batchService.deleteBatchService(id);
	}
}
