package it.unibo.droneMission.prototypes.messages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class Factory {
	
	public static Command createCommand(String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		boolean hasvalue = object.get("hasvalue").getAsBoolean();
		int value = object.get("value").getAsInt();
		int type = object.get("type").getAsInt();
		
		if(hasvalue)
			return new Command(type, value);
		else
			return new Command(type);		
	}
	
	public static Reply createReply(String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		boolean hasmessage = object.get("hasmessage").getAsBoolean();
		String message = "";
		if (hasmessage)
			message = object.get("message").getAsString();
		int type = object.get("type").getAsInt();
		
		if(hasmessage)
			return new Reply(type, message);
		else
			return new Reply(type);		
	}
}
