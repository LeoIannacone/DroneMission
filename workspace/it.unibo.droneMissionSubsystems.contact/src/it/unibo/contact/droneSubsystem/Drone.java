package it.unibo.contact.droneSubsystem;

import it.unibo.contact.droneSubsystem.DroneSupport;

public class Drone extends DroneSupport {
	
	protected double speed;
	protected double km;
	protected double fuel;
	protected double lat;
	protected double lon;

	public Drone(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDrone() throws Exception {
		// TODO Auto-generated method stub
		speed=300;
		km=0;
		fuel=DefaultValues.STARTFUEL;
		lat=0;
		lon=0;
	}

	@Override
	protected String updateStatus(String sensor) throws Exception {
		if(sensor.contains("speed")){
			return ("speed updated");
			//necessary?
		}
		if(sensor.contains("odo")){
			km+=(speed/3600)*DefaultValues.ODOTIME;
			return ("new km: "+km);
		}
		if(sensor.contains("fuel")){
			fuel-=((speed*30)/3600)*DefaultValues.FUELTIME;
			return ("new fuel: "+fuel);
		}
		if(sensor.contains("loc")){
			return ("location updated");
			//???
		}
		return("error");
		
	}

	@Override
	protected String preparePhotoPack() throws Exception {
		// TODO Auto-generated method stub
		return ("Speed: " + speed + ";\nkm: "+ km + ";\nfuel: "+ fuel +";\nlatitude: " + lat + ";\nlongitude: " + lon);
	}

	@Override
	protected void sendPhotoToHeadQuarter(String packet) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean fuelEmpty() throws Exception {
		// TODO Auto-generated method stub
		if (fuel<DefaultValues.MINFUEL)
			return false;
		else
			return true;
	}

}
