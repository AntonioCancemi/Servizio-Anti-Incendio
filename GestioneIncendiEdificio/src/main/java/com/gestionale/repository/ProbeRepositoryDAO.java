package com.gestionale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionale.enumeration.AreasOrientation;
import com.gestionale.model.Probe;

@Repository
public interface ProbeRepositoryDAO extends JpaRepository<Probe, Long> {
	List<Probe> findByFloorAndOrientation(int floor, AreasOrientation orientation);
}
