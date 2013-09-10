package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IMessageValueAsInt;

public class MessageTypeAsIntValueAsInt extends MessageTypeAsInt implements IMessageValueAsInt {

	protected int value;
	
	public MessageTypeAsIntValueAsInt(int type) {
		this.type = type;
	}
	
	public MessageTypeAsIntValueAsInt(int type, int value) {
		this.type = type;
		this.value = value;
		this.hasvalue = true;
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
	
	@Override
	public void deleteValue() {
		this.hasvalue = false;
		this.value = 0;
	}
	
}
