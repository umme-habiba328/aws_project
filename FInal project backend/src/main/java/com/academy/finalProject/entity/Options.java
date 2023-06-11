package com.academy.finalProject.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "options")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Options implements Comparable<Options>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String optionDescription;
	
	@Transient
	@ManyToOne
	@JsonBackReference
	private Questions question;

	@Override
	public int compareTo(Options o) {
		if(id == o.getId()) {
			return 0;
		}
		return 1;
	}
}
