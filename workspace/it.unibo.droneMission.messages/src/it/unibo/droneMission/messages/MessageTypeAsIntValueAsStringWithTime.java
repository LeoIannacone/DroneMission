package it.unibo.droneMission.messages;

import java.util.Date;

import it.unibo.droneMission.interfaces.messages.IMessageWithTime;

public class MessageTypeAsIntValueAsStringWithTime extends
		MessageTypeAsIntValueAsString implements IMessageWithTime {

	protected long time;
	
	public MessageTypeAsIntValueAsStringWithTime(int type) {
		super(type);
		this.time = new Date().getTime();
	}

	public MessageTypeAsIntValueAsStringWithTime(int type, long time) {
		super(type);
		this.time = time;
	}

	
	public MessageTypeAsIntValueAsStringWithTime(int type, String value) {
		super(type, value);
		this.time = new Date().getTime();
	}
	
	public MessageTypeAsIntValueAsStringWithTime(int type, String value, long time) {
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
