package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.ICommand;

public class Command extends MessageTypeAsIntValueAsIntWithTime implements ICommand {

	public Command(int type) {
		super(type);
	}

	public Command(int type, int value) {
		super(type, value);
	}
	
	public Command(int type, long time) {
		super(type, time);
	}
	
	public Command(int type, int value, long time) {
		super(type, value, time);
	}
	
	public String toString() {
		if (this.hasvalue)
			return String.format("CMD: type[%d] - value[%d]", this.type, this.value);
		return String.format("CMD: type[%d] - novalue", this.type);
	}
	
}
