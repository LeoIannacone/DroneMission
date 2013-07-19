package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IReply;


public class Reply extends MessageTypeAsIntValueAsString implements IReply {

	private int type;
	private String message;
	private boolean hasmessage;
	
	public Reply(int type) {
		this.type = type;
		this.hasmessage = false;
	}
	
	public Reply(int type, String message) {
		this.type = type;
		this.message = message;
		this.hasmessage = true;
	}
		
	public String toString() {
		if (hasmessage)
			return String.format("REPLY: type[%d] - message[\"%s\"]", this.type, this.message);
		return String.format("REPLY: type[%d] - nomessage", this.type);
	}
	
}
