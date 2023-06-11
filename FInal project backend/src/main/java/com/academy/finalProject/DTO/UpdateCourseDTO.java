package com.academy.finalProject.DTO;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseDTO {

	private Long id;
	private String title;
	private String batch;
	private String status;
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date endDate;
	
	private List<String> topics;
}
