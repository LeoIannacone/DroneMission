package it.unibo.droneMission.prototypes.messages;

import java.util.Formatter;

import it.unibo.droneMission.interfaces.messages.ICommand;

public class Command extends Message implements ICommand {

	private int type;
	private int value;
	private boolean hasvalue = false;
	
	public Command(int type) {
		this.type = type;
	}
	
	public Command(int type, int value) {
		this.type = type;
		this.value = value;
		this.hasvalue = true;
	}
		
	
	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public boolean hasValue() {
		return hasvalue;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setValue(int value) {
		this.hasvalue = true;
		this.value = value;
	}
	
	public String toString() {
		if (hasvalue)
			return String.format("CMD: type[%d] - value[%d]", this.type, this.value);
		return String.format("CMD: type[%d] - novalue", this.type);
	}
	
}
