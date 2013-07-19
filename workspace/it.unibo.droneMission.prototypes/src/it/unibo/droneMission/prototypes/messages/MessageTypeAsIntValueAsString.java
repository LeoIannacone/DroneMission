package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IMessageValueAsString;

public abstract class MessageTypeAsIntValueAsString extends MessageTypeAsInt implements IMessageValueAsString {

	protected boolean hasmessage;
	protected String message;
	
	@Override
	public boolean hasMessage() {
		return hasmessage;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(String message) {
		this.hasmessage = true;
		this.message= message;
	}
	
}
