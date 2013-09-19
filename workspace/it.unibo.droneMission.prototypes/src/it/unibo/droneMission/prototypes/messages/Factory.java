package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.gauges.IGauge;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public abstract class Factory {
	
	public static Command createCommand(String json) {
		// return (Command) createTypeAsIntValueAsInt(json)
		MessageTypeAsIntValueAsIntWithTime m = createTypeAsIntValueAsInt(json);
		if(m.hasValue())
			return new Command(m.getType(), m.getValue(), m.getTime());
		else
			return new Command(m.getType(), m.getTime());
	}
		
	public static Reply createReply(String json) {
		//return (Reply) createTypeAsIntValueAsString(json);
		MessageTypeAsIntValueAsStringWithTime m = createTypeAsIntValueAsString(json);
		if(m.hasValue())
			return new Reply(m.getType(), m.getValue(), m.getTime());
		else
			return new Reply(m.getType(), m.getTime());
	}
	
	public static Notify createNotify(String json) {
		//return (Reply) createTypeAsIntValueAsString(json);
		MessageTypeAsIntValueAsStringWithTime m = createTypeAsIntValueAsString(json);
		if(m.hasValue())
			return new Notify(m.getType(), m.getValue(), m.getTime());
		else
			return new Notify(m.getType(), m.getTime());
	}
	
	public static SensorsData createSensorsData(String json)
	{
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		long time = object.get("time").getAsLong();
		
		Gson gson = new Gson();		
		List<Sensor> sensors = gson.fromJson(object.get("sensors"),new TypeToken<List<Sensor>>(){}.getType());
		
		SensorsData sensorsData = new SensorsData();
		sensorsData.setTime(time);
		
		for(Sensor s : sensors)
		{
			sensorsData.addGauge(Utils.fromSensorToGauge(s));
		}
		return sensorsData;
		
	}
	
	public static List<IGauge> createGaugesList(String json) throws Exception {
		return null;
	}
	
	public static Sensor createSensor(String json) throws Exception {
		MessageTypeAsIntValueAsString m = createTypeAsIntValueAsString(json);
		if (m.hasvalue) {
			return new Sensor(m.getType(), m.getValue());
		}
		else
			throw new Exception("SensordData Message has no value");
	}
	
	public static PicturePackage createPicturePackage(String json) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
				
		JsonObject sensorsJSON = object.get("sensorsData").getAsJsonObject();
		JsonObject fileJSON = object.get("file").getAsJsonObject();
		
		SensorsData sensors = createSensorsData(gson.toJson(sensorsJSON));
		File file = createFile(gson.toJson(fileJSON));
		
		return new PicturePackage(sensors, file);
	}
	
	public static File createFile(String json) {
		
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		long creationTime = object.get("creationTime").getAsLong();
		String name = object.get("name").getAsString();
		String data = object.get("data").getAsString();
		
		File file = new File();
		file.setCreationTime(creationTime);
		file.setName(name);
		file.setData(data);
		return file;
	}

	private static MessageTypeAsIntValueAsIntWithTime createTypeAsIntValueAsInt(String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		boolean hasvalue = object.get("hasvalue").getAsBoolean();
		int value = object.get("value").getAsInt();
		int type = object.get("type").getAsInt();
		long time = object.get("time").getAsLong();
		
		if(hasvalue)
			return new MessageTypeAsIntValueAsIntWithTime(type, value, time);
		else
			return new MessageTypeAsIntValueAsIntWithTime(type, time);		
	}
	
	private static MessageTypeAsIntValueAsStringWithTime createTypeAsIntValueAsString(String json) {
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();
		
		boolean hasvalue = object.get("hasvalue").getAsBoolean();
		String value = "";
		if (hasvalue)
			value = object.get("value").getAsString();
		int type = object.get("type").getAsInt();
		long time = object.get("time").getAsLong();
		
		if(hasvalue)
			return new MessageTypeAsIntValueAsStringWithTime(type, value, time);
		else
			return new MessageTypeAsIntValueAsStringWithTime(type, time);			
	}
	
	
}
