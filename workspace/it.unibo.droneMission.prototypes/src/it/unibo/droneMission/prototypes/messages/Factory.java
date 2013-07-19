package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.IMessage;
import it.unibo.droneMission.interfaces.messages.IMessageValue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class Factory {
	
	public static Command createCommand(String json) {
		// return (Command) createTypeAsIntValueAsInt(json)
		MessageTypeAsIntValueAsInt m = createTypeAsIntValueAsInt(json);
		if(m.hasValue())
			return new Command(m.getType(), m.getValue());
		else
			return new Command(m.getType());
	}
		
	public static Reply createReply(String json) {
		//return (Reply) createTypeAsIntValueAsString(json);
		MessageTypeAsIntValueAsString m = createTypeAsIntValueAsString(json);
		if(m.hasValue())
			return new Reply(m.getType(), m.getValue());
		else
			return new Reply(m.getType());
	}
	

	private static MessageTypeAsIntValueAsInt createTypeAsIntValueAsInt(String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		boolean hasvalue = object.get("hasvalue").getAsBoolean();
		int value = object.get("value").getAsInt();
		int type = object.get("type").getAsInt();
		
		if(hasvalue)
			return new MessageTypeAsIntValueAsInt(type, value);
		else
			return new MessageTypeAsIntValueAsInt(type);		
	}
	
	private static MessageTypeAsIntValueAsString createTypeAsIntValueAsString(String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		boolean hasvalue = object.get("hasvalue").getAsBoolean();
		String value = "";
		if (hasvalue)
			value = object.get("value").getAsString();
		int type = object.get("type").getAsInt();
		
		if(hasvalue)
			return new MessageTypeAsIntValueAsString(type, value);
		else
			return new MessageTypeAsIntValueAsString(type);			
	}
	
	
}