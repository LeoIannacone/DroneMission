package it.unibo.contact.DroneMissionSystem;

import it.unibo.contact.DroneMissionSystem.SmartphoneSupport;

public class Smartphone extends SmartphoneSupport {
	public Smartphone(String s) throws Exception{
		super(s);
	}

	@Override
	protected void notifyUserMissionStarted() throws Exception {
		env.println("MISSION_STARTED");
		
	}
	@Override
	protected void showDataSensorsReceived(String data) throws Exception {
		env.println("DATA DRONE: " + data);
		
	}

	@Override
	protected void missionFinished() throws Exception {
		env.println("MISSION END");		
	}

}
