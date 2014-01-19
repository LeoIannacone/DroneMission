package it.unibo.contact.DroneMissionSystem;


import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.messages.SensorsData;
import it.unibo.droneMission.messages.Utils;

public class Drone extends DroneSupport {
	
	protected Odometer odo;
	protected Speedometer speed;
	protected Fuelometer fuel;
	protected LocTracker loctracker;
	
	public Drone(String s) throws Exception{
		super(s);
		odo = new Odometer();
		speed = new Speedometer();
		fuel = new Fuelometer();
		loctracker = new LocTracker();
	}
	
	
	/*
	 * Considering cmdString is in format
	 * cmdName value
	 */
	
	private static String cleanCommand(String cmdString) {
		return cmdString.replace("\"","").replace("'","");
	}
	
	public static String getCommandName(String cmdString) {
		return cleanCommand(cmdString).split(" ")[0] ;
	}
	
	public static String getCommandValue(String cmdString) {
		cmdString = cleanCommand(cmdString);
		String[] info = cmdString.split(" ");
		if (info.length >= 1)
			return info[1];
		return "";
	}
	
	
	public void setSpeed() {
		env.println("SET SPEED: " + curInputMsgContent);
	}

	@Override
	protected void setSpeed(String value) throws Exception {
		env.println("SET SPEED: " + value);
	}

	@Override
	protected void startMission() throws Exception {
		env.println("START MISSION");
	}


	@Override
	protected void endMission() throws Exception {
		env.println("STOP MISSION");
	}


	@Override
	protected String getDataFromSensors() throws Exception {
		odo.setVal(new GaugeValueInt((int)(Math.random() * 100)));
		speed.setVal(new GaugeValueInt((int)(Math.random() * 100)));
		fuel.setVal(new GaugeValueInt((int)(Math.random() * 100)));
		loctracker.update(new GaugeValueDouble(Math.random() * 100), new GaugeValueDouble(Math.random() * 100));
		SensorsData sensors = new SensorsData();
		sensors.addGauge(odo);
		sensors.addGauge(speed);
		sensors.addGauge(fuel);
		sensors.addGauge(loctracker);
		
		return Utils.adaptJSONToContact(sensors.toJSON());
	}


	@Override
	protected String getDataPhoto() throws Exception {
		return "photoX;dataY;timeZ";
	}


	//@Override
	protected boolean executeCommand(String cmd, String v) throws Exception {
		if(cmd.equals("setspeed") && (Integer.parseInt(v)>=60) && (Integer.parseInt(v)<=120)){
			env.println("Set speed to: " + v);
			return true;
		}
		if(cmd.equals("start")){
			env.println("Mission started");
			return true;
		}
		if(cmd.equals("stop")){
			env.println("Mission stopped");
			this.stop=true;
			return true;
		}
		return false;
	}
}
