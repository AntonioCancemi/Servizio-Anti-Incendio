package com.gestionale.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionale.controller.AlarmController;
import com.gestionale.model.Probe;
import com.gestionale.repository.ProbeRepositoryDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProbeService {
	@Autowired
	ProbeRepositoryDAO repo;
	private static final Logger log = LoggerFactory.getLogger(AlarmController.class);

	public Probe create(Probe probe) {

		return repo.save(probe);
	}

	public Probe update(Long id, Probe probe) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Probe[id:" + id + "] not Found");
		}
		if (id != probe.getId()) {
			throw new EntityNotFoundException("ID param doesnt match with request body probe ID");
		}
		return repo.save(probe);
	}

	public String remove(Probe probe) {
		if (repo.findByFloorAndOrientation(probe.getFloor(), probe.getOrientation()).isEmpty()) {
			throw new EntityNotFoundException("probe[id:" + probe.getId() + "] dosent exits ");
		}
		repo.delete(probe);
		return "probe  removed!!!";
	}

	public String removeById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("probe[id:" + id + "] dosent exits");
		}
		repo.deleteById(id);
		return "probe  removed!!!";
	}

	public List<Probe> getAll() {
		if (repo.findAll().isEmpty()) {
			throw new EntityNotFoundException("No entity Found");
		}
		return repo.findAll();
	}

}
