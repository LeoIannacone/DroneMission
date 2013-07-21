package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.ISensor;

public class Sensor extends MessageTypeAsIntValueAsString implements ISensor {

	public Sensor(int type, String value) {
		super(type, value);
	}
}
