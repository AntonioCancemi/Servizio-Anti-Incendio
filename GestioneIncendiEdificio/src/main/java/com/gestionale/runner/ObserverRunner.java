package com.gestionale.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gestionale.model.ServerListener;
import com.gestionale.service.ProbeAlarmService;

@Component
public class ObserverRunner implements ApplicationRunner {
	@Autowired
	ProbeAlarmService alarmService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ServerListener observer1 = new ServerListener();
		observer1.setUrl("http://localhost:8081/api/GestionaleIncendiCentrale/alarm");
		alarmService.registerObserver(observer1);
	}

}
