package it.unibo.contact.DroneSmartDashboard;

import it.unibo.droneMission.smartDevice.android.SmartDashboard;

public class DroneSmartDashboard extends DroneSmartDashboardMain {

	protected SmartDashboard ba;
	
	public DroneSmartDashboard(SmartDashboard smartDashboard) {
		super();
		this.ba=smartDashboard;		
	}
	public DroneSmartDashboard() {

	}
	@Override
	protected void configureSubjects(){
		try {
			smartdevice = new Smartdevice(ba);
			smartdevice.setName("Smart Dashboard");
			smartdevice.setEnv(env);
			smartdevice.initInputSupports();
			registerObservers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void start(){
		smartdevice.start();
	}

}
