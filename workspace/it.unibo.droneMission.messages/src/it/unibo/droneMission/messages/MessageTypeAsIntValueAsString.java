package it.unibo.droneMission.messages;

import it.unibo.droneMission.interfaces.messages.IMessageValueAsString;

public class MessageTypeAsIntValueAsString extends MessageTypeAsInt implements IMessageValueAsString {

	protected String value;
	
	public MessageTypeAsIntValueAsString(int type) {
		super.type = type;
		super.hasvalue = false;
	}	
	
	public MessageTypeAsIntValueAsString(int type, String value) {
		super.type = type;
		this.value = value;
		super.hasvalue = true;
	}
	
	@Override
	public boolean hasValue() {
		return hasvalue;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public void setValue(String value) {
		this.hasvalue = true;
		this.value = value;
	}
	
	@Override
	public void deleteValue() {
		this.hasvalue = false;
		this.value = null;
	}
	
}
