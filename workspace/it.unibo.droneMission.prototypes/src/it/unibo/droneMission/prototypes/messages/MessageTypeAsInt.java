package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IMessageTypeAsInt;

public abstract class MessageTypeAsInt extends Message implements IMessageTypeAsInt {
	protected int type;
	protected boolean hasvalue = false;
	
	@Override
	public int getType() {
		return this.type;
	}
	
	@Override
	public void setType(int type) {
		this.type = type;
	}
}
