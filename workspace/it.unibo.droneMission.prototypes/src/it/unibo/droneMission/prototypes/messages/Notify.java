package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.INotify;

public class Notify extends MessageTypeAsIntValueAsString implements INotify {

	public Notify(int type) {
		super(type);
	}	
	
	public Notify(int type, String value) {
		super(type, value);
	}
		
	public String toString() {
		if (hasvalue)
			return String.format("NOTIFY: type[%d] - message[\"%s\"]", super.type, super.value);
		return String.format("NOTIFY: type[%d] - nomessage", super.type);
	}
	
}
