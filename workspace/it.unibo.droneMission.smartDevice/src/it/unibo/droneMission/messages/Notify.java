package it.unibo.droneMission.messages;

import it.unibo.droneMission.interfaces.messages.INotify;

public class Notify extends MessageTypeAsIntValueAsStringWithTime implements INotify {

	public Notify(int type) {
		super(type);
	}	
	
	public Notify(int type, String value) {
		super(type, value);
	}
	
	public Notify(int type, long time) {
		super(type, time);
	}
	
	public Notify(int type, String value, long time) {
		super(type, value, time);
	}
		
	public String toString() {
		if (hasvalue)
			return String.format("NOTIFY: type[%d] - message[\"%s\"]", super.type, super.value);
		return String.format("NOTIFY: type[%d] - nomessage", super.type);
	}
	
}
