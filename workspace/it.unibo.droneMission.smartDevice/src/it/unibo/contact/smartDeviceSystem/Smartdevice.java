package it.unibo.contact.SmartDeviceSystem;

import java.util.ArrayList;
import java.util.List;

import it.unibo.droneMission.gauge.*;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.Sensor;
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
		SensorsData sensorsData = Factory.createSensorsData(data);
		updateGaugesValues(sensorsData.getGauges());
		
	}

	protected void updateGaugesValues(List<IGauge> list){
		for(IGauge g : list) {
			String type = Utils.getGaugeName(Utils.getGaugeType(g));
			if (type.equals("Fuelometer"))
				fuel.setVal(g.getVal());
			if (type.equals("Speedometer"))
				speed.setVal(g.getVal());
			if (type.equals("Odometer"))
				odo.setVal(g.getVal());
			if (type.equals("Loctracker")){
				LocTracker l = (LocTracker)g;
				loctracker.update(l.getLat(), l.getLon());
			}
		}
	}

}
