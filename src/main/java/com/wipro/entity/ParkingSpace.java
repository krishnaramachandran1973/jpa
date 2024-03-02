package com.wipro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@Entity
//@Table(name = "parking_space")
public class ParkingSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String parkingSpot;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(mappedBy = "parkingSpace", fetch = FetchType.LAZY)
	private Employee employee;

}
