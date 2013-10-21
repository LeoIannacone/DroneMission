package it.unibo.contact.DroneMissionSystem;

import java.util.List;

import it.unibo.droneMission.gauge.*;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.SensorsData;
import it.unibo.droneMission.messages.Utils;


public class Smartdevice extends SmartdeviceSupport {
	
	protected Odometer odo;
	protected Speedometer speed;
	protected Fuelometer fuel;
	protected LocTracker loctracker;
	
	public Smartdevice(String s) throws Exception{
		super(s);
		odo = new Odometer();
		speed = new Speedometer();
		fuel = new Fuelometer();
		loctracker = new LocTracker();
	}

	@Override
	protected void notifyUserMissionStarted() throws Exception {
		env.println("Received \"Mission Started\" from Drone");
		
	}

	@Override
	protected void missionFinished() throws Exception {
		env.println("Received \"Mission Terminated\" from Drone");		
	}

	@Override
	protected void updateGauges(String data) throws Exception {
		try{
		SensorsData sensorsData = Factory.createSensorsData(Utils.cleanJSONFromContact(data));
		for(IGauge g : sensorsData.getGauges()) {
			int t = Utils.getGaugeType(g);
			String type = Utils.getGaugeName(t);
			if (type.equals("Fuelometer"))
				env.println("Fuel = " + g.getCurValRepDisplayed());
			if (type.equals("Speedometer"))
				env.println("Speed = " + g.getCurValRepDisplayed());
			if (type.equals("Odometer"))
				env.println("Odo = " + g.getCurValRepDisplayed());
			if (type.equals("Loctracker")){
				env.println("Coords = " + g.getCurValRepDisplayed());
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void updateGaugesValues(List<IGauge> list){
		
	}

}
