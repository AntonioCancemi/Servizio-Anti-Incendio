package com.gestionale.model;

import com.gestionale.enumeration.AreasOrientation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "probes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Probe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int lat;
	private int lon;
	@Column(name = "smoke_level")
	private int smokeLevel;
	@Enumerated(EnumType.STRING)
	private AreasOrientation orientation;
	private int floor;

}
