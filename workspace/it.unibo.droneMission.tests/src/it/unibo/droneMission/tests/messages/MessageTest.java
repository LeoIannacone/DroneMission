package it.unibo.droneMission.tests.messages;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.prototypes.messages.File;
import it.unibo.droneMission.prototypes.messages.Sensor;
import it.unibo.droneMission.prototypes.messages.SensorsData;
import it.unibo.droneMission.prototypes.messages.Utils;
public class MessageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testFile();
	}
	
	public static void testFile() {
		
		String fileToEncode = "/home/l3on/tmp/image.jpg";
		String fileDecoded = "/home/l3on/tmp/test.jpg";
		
		File f = new File(fileToEncode);
		java.io.File file = new java.io.File(fileToEncode);
		String base64 = Utils.encodeFileToBase64(file);
		System.out.println(base64);
		byte[] content = Utils.decodeFileFromBase64(base64);
		
		try {
			java.io.File test = new java.io.File(fileDecoded);
			test.createNewFile();
			FileOutputStream out = new FileOutputStream(test);
			out.write(content);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(f.toJSON());
	}
	
	public static void testSensorsData() {
		
		SensorsData sensors = new SensorsData();
		
		Odometer o = new Odometer();
		o.setVal(new GaugeValueInt(3));
		sensors.addGauge(o);
		
		Fuelometer f = new Fuelometer();
		f.setVal(new GaugeValueDouble(12.3));
		sensors.addGauge(f);
		
		Speedometer s = new Speedometer();
		s.setVal(new GaugeValueInt(130));
		sensors.addGauge(s);
		
		String json = sensors.toJSON();
		System.out.println("FULL JSON: " + json);
		
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();		
		JsonElement jsonSensors = object.get("sensors");
		Gson gson = new Gson();		
		List<Sensor> se = gson.fromJson(jsonSensors,new TypeToken<List<Sensor>>(){}.getType());
		
		System.out.println(se);
		
	}
	
	public static void testSensor( ) {
		Sensor c = new Sensor(TypesCommand.START_MISSION, "2.34");
//		c.setValue(3);
		System.out.println(c);
		String json = c.toJSON();
		System.out.println(json);
		
		//Command c1 = (Command) Factory.createCommand(json);
		
		//System.out.println(c1);
	}

}
