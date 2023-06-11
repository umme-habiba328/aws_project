package com.academy.finalProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.finalProject.entity.Options;

public interface OptionsRepository extends JpaRepository<Options, Long>{

	public Options findByOptionDescription(String optionDescription);
	
	public Options findByOptionDescriptionAndQuestionId(String optionDescription,Long questionId);
	
	public List<Options> findByQuestionId(Long questionId);
}
