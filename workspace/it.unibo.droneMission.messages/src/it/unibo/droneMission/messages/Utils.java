package it.unibo.droneMission.messages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensor;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.interfaces.messages.TypesReply;
import it.unibo.droneMission.interfaces.messages.TypesSensor;


public class Utils {
	
	public static final String LOCTRACKER_VALUE_SEPARATOR = ";";
	
	public static ISensor fromGaugeToSensor(IGauge gauge) {
		int type = -1;
		Class<?> cls = gauge.getClass();
		String value;
		
		if (cls == LocTracker.class) {
			type = TypesSensor.LOCTRACKER;
			LocTracker locTracker = (LocTracker) gauge;
			value = String.format("%s%s%s", 
					locTracker.getLat().valAsDouble(),
					LOCTRACKER_VALUE_SEPARATOR,
					locTracker.getLon().valAsDouble()
					);
			return new Sensor(type, value);
		}
		 value = "" + gauge.getVal().valAsDouble();
		
	
		if (cls == Odometer.class)
			type = TypesSensor.ODOMETER;
		
		else if (cls == Speedometer.class)
			type = TypesSensor.SPEEDOMETER;
		
		else if (cls == Fuelometer.class)
			type = TypesSensor.FUELOMETER;
	
	

		return new Sensor(type, value);
	}
	
	public static IGauge fromSensorToGauge(ISensor sensor) {
		int type = sensor.getType();
		
		IGauge g = null;
		
		if (type == TypesSensor.LOCTRACKER) 
		{
			String[] info = sensor.getValue().split(LOCTRACKER_VALUE_SEPARATOR);
			GaugeValueDouble latitude = new GaugeValueDouble(Double.parseDouble(info[0]));
			GaugeValueDouble longitude = new GaugeValueDouble(Double.parseDouble(info[1]));
			g = new LocTracker(latitude, longitude);;
		}
		
		else {
			GaugeValueInt valueInt = new GaugeValueInt((int) Double.parseDouble(sensor.getValue()));
			GaugeValueDouble valueDouble = new GaugeValueDouble(Double.parseDouble(sensor.getValue()));
			
			if (type == TypesSensor.FUELOMETER) {
				g = new Fuelometer();
				g.setVal(valueDouble);
			}
			
			else if (type == TypesSensor.ODOMETER) {
				g = new Odometer();
				g.setVal(valueInt);
			}
			
			else if (type == TypesSensor.SPEEDOMETER) {
				g = new Speedometer();
				g.setVal(valueInt);
			}
		}
		
		return g;
	}
	
	public static byte[] decodeFileFromBase64(String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(base64);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encodeFileToBase64(java.io.File file) {
		BASE64Encoder encoder = new BASE64Encoder();
		byte fileContent[] = new byte[(int)file.length()];
		FileInputStream f;
		try {
			f = new FileInputStream(file);
			try {
				f.read(fileContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return encoder.encodeBuffer(fileContent);
	}
	
	public static String getGaugeName(int gaugeType) {
		
		if (gaugeType == TypesSensor.FUELOMETER)
			return "Fuelometer";
		else if (gaugeType == TypesSensor.LOCTRACKER)
			return "Loctracker";
		else if (gaugeType == TypesSensor.ODOMETER)
			return "Odometer";
		else if (gaugeType == TypesSensor.SPEEDOMETER)
			return "Speedometer";
				
		return "";
	}
	
	public static int getGaugeType(IGauge gauge) {
		
		Class<?> cls = gauge.getClass();
		
		if (cls == Fuelometer.class)
			return TypesSensor.FUELOMETER;
		else if (cls == LocTracker.class)
			return TypesSensor.LOCTRACKER;
		else if (cls == Odometer.class)
			return TypesSensor.ODOMETER;
		else if (cls == Speedometer.class)
			return TypesSensor.SPEEDOMETER;
				
		return -1;
	}
	
	public static String getCommandName(ICommand command) {
		int type = command.getType();
		if (type == TypesCommand.SPEED_SET)
			return "Set speed";
		if (type == TypesCommand.SPEED_INCREASE)
			return "Speed increase";
		if (type == TypesCommand.SPEED_DECREASE)
			return "Speed decrease";
		if (type == TypesCommand.START_MISSION)
			return "Start Mission";
		
		return "";
	}

	public static String getReplyName(IReply reply) {
		int type = reply.getType();
		if (type == TypesReply.REPLY_FAIL)
			return "Fail";
		if (type == TypesReply.REPLY_NO)
			return "No";
		if (type == TypesReply.REPLY_OK)
			return "Ok";
		return "";
	}
	
	
	private static String formatReplaceString(String s) {
		String simbol = "";
		String pre = simbol + "rpl";
		String post = simbol;
		return String.format("%s%s%s", pre, s, post);
	}
	
	private static Hashtable<String, String> getReplaceDict() {
		
		Hashtable<String, String> dict = new Hashtable<>();
		dict.put("\"", "@@@");
//		dict.put(".", formatReplaceString("Dot"));
//		dict.put(",", formatReplaceString("Comma"));
//		dict.put(":", formatReplaceString("Colon"));
//		dict.put(";", formatReplaceString("SemiColon"));
//		dict.put("\"", formatReplaceString("Quotes"));
//		dict.put("[", formatReplaceString("SquareBracketsLEFT"));
//		dict.put("]", formatReplaceString("SquareBracketsRIGHT"));
//		dict.put("{", formatReplaceString("BracketsLEFT"));
//		dict.put("}", formatReplaceString("BracketsRIGHT"));
		
		return dict;
	}
	

	
	
	public static String adaptJSONToContact(String json) {
		Hashtable<String, String> dict = getReplaceDict();
		for (String key : dict.keySet()) {
			json = json.replace(key, dict.get(key));
		}
		return json.trim();
	}
	
	public static String cleanJSONFromContact(String message) {
		
		String json = message; 
		
		if(json.startsWith("\'") || json.startsWith("\""))
			json=json.substring(1, json.length()-1);
		if(json.endsWith("\'") || json.endsWith("\""))
			json=json.substring(0, json.length()-2);
		
		Hashtable<String, String> dict = getReplaceDict();
		for (String key : dict.keySet()) {
			json = json.replace(dict.get(key), key);
		}
		return json.trim();
	}
}
