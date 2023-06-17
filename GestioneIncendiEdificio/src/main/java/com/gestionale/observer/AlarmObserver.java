package com.gestionale.observer;

import com.gestionale.model.Probe;

public interface AlarmObserver {

	void sendAlarm(Probe alarmData);
}
