package it.unibo.droneMission.messages;

import it.unibo.droneMission.interfaces.messages.ISensor;

public class Sensor extends MessageTypeAsIntValueAsString implements ISensor {

	public Sensor(int type, String value) {
		super(type, value);
	}
	
	public String toString() {
		return String.format("SENSOR: type[%d] - value[%s]", type, value);
	}
}
