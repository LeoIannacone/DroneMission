package it.unibo.droneMission.messages;

import java.util.Date;

import it.unibo.droneMission.interfaces.messages.IMessageWithTime;

public class MessageTypeAsIntValueAsIntWithTime extends
		MessageTypeAsIntValueAsInt implements IMessageWithTime {

	protected long time;
	
	public MessageTypeAsIntValueAsIntWithTime(int type) {
		super(type);
		this.time = new Date().getTime();
	}
	
	public MessageTypeAsIntValueAsIntWithTime(int type, int value) {
		super(type,value);
		this.time = new Date().getTime();
	}
	
	public MessageTypeAsIntValueAsIntWithTime(int type, long time) {
		super(type);
		this.time = time;
	}

	
	public MessageTypeAsIntValueAsIntWithTime(int type, int value, long time) {
		super(type, value);
		this.time = time;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public void setTime(long time) {
		this.time = time;		
	}

}
