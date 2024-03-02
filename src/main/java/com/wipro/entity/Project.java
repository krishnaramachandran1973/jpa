package com.wipro.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Builder
@Data
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Default
	@ManyToMany(mappedBy = "projects")
	private Set<Employee> employees = new HashSet<>();

}
