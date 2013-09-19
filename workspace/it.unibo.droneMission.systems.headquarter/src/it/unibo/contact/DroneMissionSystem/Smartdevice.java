package it.unibo.contact.DroneMissionSystem;

import it.unibo.contact.DroneMissionSystem.SmartdeviceSupport;

public class Smartdevice extends SmartdeviceSupport {
	public Smartdevice(String s) throws Exception{
		super(s);
	}

	@Override
	protected void notifyUserMissionStarted() throws Exception {
		env.println("MISSION_STARTED");
		
	}

	@Override
	protected void missionFinished() throws Exception {
		env.println("MISSION END");		
	}

	@Override
	protected void showSensorsDatasReceived(String data) throws Exception {
		env.println("DATA DRONE: " + data);
	}

}