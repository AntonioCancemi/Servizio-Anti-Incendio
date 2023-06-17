package com.gestionale.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.factory.AlarmFactory;
import com.gestionale.model.Probe;
import com.gestionale.service.ProbeService;

@RestController
@RequestMapping("/api/GestionaleIncendiCentrale/alarm")
public class AlarmController {
	private static final Logger log = LoggerFactory.getLogger(AlarmController.class);

	@Autowired
	private ProbeService probeService;

	@GetMapping
	public ResponseEntity<?> getAll() {

		return ResponseEntity.ok(probeService.getAll());
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Probe probe) {
		log.info("ALARM RECIVED");
		log.error("-----------------------------------------------------");
		log.warn(AlarmFactory.createAlarm(probe));
		log.error("-----------------------------------------------------");
		return ResponseEntity.ok(probeService.create(probe));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Probe probe) {

		return ResponseEntity.ok(probeService.update(id, probe));
	}

	@DeleteMapping
	public ResponseEntity<?> remove(@RequestBody Probe probe) {
		return ResponseEntity.ok(probeService.remove(probe));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(probeService.removeById(id));
	}

}
