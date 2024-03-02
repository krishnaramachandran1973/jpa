package com.wipro.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Builder
@Data
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Default
	@OneToMany(mappedBy = "department")
	private Set<Employee> employees = new HashSet<>();

}
