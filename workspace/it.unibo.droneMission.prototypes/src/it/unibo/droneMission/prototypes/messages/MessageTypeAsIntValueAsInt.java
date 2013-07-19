package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IMessageValueAsInt;

public abstract class MessageTypeAsIntValueAsInt extends MessageTypeAsInt implements IMessageValueAsInt {

	protected boolean hasvalue;
	protected int value;
	
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

}
