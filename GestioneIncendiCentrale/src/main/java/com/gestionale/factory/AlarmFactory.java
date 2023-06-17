package com.gestionale.factory;

import com.gestionale.enumeration.EmergencyCode;
import com.gestionale.model.Probe;

public class AlarmFactory {
	// Patter factory semplificato
	public static String createAlarm(Probe alarm) {
		int sl = alarm.getSmokeLevel();

		if (sl > 5 && sl < 7) {
			return alarmStringGenerator(EmergencyCode.ARANCIONE, alarm);
		}
		if (sl >= 7 && sl < 9) {
			return alarmStringGenerator(EmergencyCode.ROSSO, alarm);
		}
		if (sl == 10) {

			return alarmStringGenerator(EmergencyCode.NERO, alarm);
		}

		return null;

	}

	public static String alarmStringGenerator(EmergencyCode code, Probe alarm) {
		return "\nALARM[" + alarm.getId() + "] CODE:[" + code + "]\nSMOKE LEVEL[" + alarm.getSmokeLevel() + "]\nLAT: "
				+ alarm.getLat() + " || LON:" + alarm.getLon() + "\nFLOOR:" + alarm.getFloor() + " || ORIENTATION: "
				+ alarm.getOrientation();
	}
}
