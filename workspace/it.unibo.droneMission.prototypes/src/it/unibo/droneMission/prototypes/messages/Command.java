package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.ICommand;

public class Command extends MessageTypeAsIntValueAsInt implements ICommand {

	public Command(int type) {
		this.type = type;
	}
	
	public Command(int type, int value) {
		this.type = type;
		this.value = value;
		this.hasvalue = true;
	}

	public String toString() {
		if (this.hasvalue)
			return String.format("CMD: type[%d] - value[%d]", this.type, this.value);
		return String.format("CMD: type[%d] - novalue", this.type);
	}
	
}
