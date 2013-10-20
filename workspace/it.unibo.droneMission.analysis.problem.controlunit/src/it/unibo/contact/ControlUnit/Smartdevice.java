package it.unibo.contact.ControlUnit;

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
	protected void updateGauges(String data) throws Exception {
		env.println("DATA DRONE: " + data);
	}

}
