package it.unibo.droneMission.tests.messages;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import it.unibo.droneMission.prototypes.messages.Command;
import it.unibo.droneMission.prototypes.messages.Factory;
import it.unibo.droneMission.prototypes.messages.Message;
import it.unibo.droneMission.prototypes.messages.Sensor;
import it.unibo.droneMission.prototypes.messages.SensorsData;
public class MessageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testSensorsData();
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
		Gson gson = new Gson();
		System.out.println(c);
		String json = c.toJSON();
		System.out.println(json);
		
		//Command c1 = (Command) Factory.createCommand(json);
		
		//System.out.println(c1);
	}

}
