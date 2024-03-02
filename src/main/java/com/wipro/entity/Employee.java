package com.wipro.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Builder
@Data
@Entity
//@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;

	private double salary;
	@Enumerated(EnumType.STRING)
	private EmployeeType type;
	private LocalDate startDate;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne
	@JoinColumn(name = "pspace_id")
	private ParkingSpace parkingSpace;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Default
	@ManyToMany
	@JoinTable(name = "emp_proj", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "proj_id"))
	private Set<Project> projects = new HashSet<>();
}
