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

import com.gestionale.model.Probe;
import com.gestionale.service.ProbeAlarmService;
import com.gestionale.service.ProbeService;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {
	private static final Logger log = LoggerFactory.getLogger(ProbeController.class);
	@Autowired
	private ProbeService probeService;
	@Autowired
	private ProbeAlarmService alarmService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(probeService.getAll());
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Probe probe) {
//		if (probe.getSmokeLevel() > 5) {
//			alarmService.sendAlarm(probe);
//		}
		return ResponseEntity.ok(probeService.create(probe));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Probe probe) {
		if (probe.getSmokeLevel() > 5) {
			log.warn("Danger Smoke level out of safe range!!");
			log.warn(probe.toString());
			alarmService.notifyObservers(probe);
		}
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
