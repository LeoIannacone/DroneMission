package it.unibo.droneMission.messages;

import it.unibo.droneMission.interfaces.messages.IReply;


public class Reply extends MessageTypeAsIntValueAsStringWithTime implements IReply {

	public Reply(int type) {
		super(type);
	}	
	
	public Reply(int type, String value) {
		super(type, value);
	}
	
	public Reply(int type, long time) {
		super(type, time);
	}
	
	public Reply(int type, String value, long time) {
		super(type, value, time);
	}
	
	public String toString() {
		if (hasvalue)
			return String.format("REPLY: type[%d] - message[\"%s\"]", super.type, super.value);
		return String.format("REPLY: type[%d] - nomessage", super.type);
	}
	
}
