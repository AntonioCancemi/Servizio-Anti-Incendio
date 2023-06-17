package com.gestionale.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestionale.model.Probe;
import com.gestionale.model.ServerListener;

@Component
public class ProbeAlarmService {
	private static final Logger log = LoggerFactory.getLogger(ProbeAlarmService.class);

	private List<ServerListener> observers = new ArrayList<>();

	public void registerObserver(ServerListener observer) {
		log.info("\n");
		log.info("url salvato per la notifica " + observer.getUrl());
		observers.add(observer);
	}

	public void removeObserver(ServerListener observer) {
		observers.remove(observer);
	}

	// notifica che parte dal Probe rest Controller
	public void notifyObservers(Probe probe) {
		for (ServerListener observer : observers) {
			try {
				observer.onProbeAlert(probe);
			} catch (JsonProcessingException e) {
				log.error("POST request faild!");
				e.printStackTrace();
			}
		}
	}
}
