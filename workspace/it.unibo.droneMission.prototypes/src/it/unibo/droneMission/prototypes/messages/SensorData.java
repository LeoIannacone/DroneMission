package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.ISensorData;

public class SensorData extends MessageTypeAsIntValueAsString implements ISensorData {

	public SensorData(int type, String value) {
		super(type, value);
	}
}
