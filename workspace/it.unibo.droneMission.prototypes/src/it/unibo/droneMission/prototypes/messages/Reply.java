package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IReply;


public class Reply extends MessageTypeAsIntValueAsString implements IReply {

	public Reply(int type) {
		super(type);
	}	
	
	public Reply(int type, String value) {
		super(type, value);
	}
		
	public String toString() {
		if (hasvalue)
			return String.format("REPLY: type[%d] - message[\"%s\"]", super.type, super.value);
		return String.format("REPLY: type[%d] - nomessage", super.type);
	}
	
}
