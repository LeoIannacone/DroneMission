package it.unibo.droneMission.messages;

import com.google.gson.Gson;

import it.unibo.droneMission.interfaces.messages.IMessage;

public abstract class Message implements IMessage {

	@Override
	public String toJSON() {
		return (new Gson()).toJson(this);
	}
}
