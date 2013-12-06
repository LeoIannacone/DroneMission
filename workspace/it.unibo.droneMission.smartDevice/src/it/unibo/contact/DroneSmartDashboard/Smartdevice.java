package it.unibo.contact.DroneSmartDashboard;

import java.sql.Time;

import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.messages.*;

import it.unibo.droneMission.smartDevice.android.SmartDashboard;

public class Smartdevice extends SmartdeviceSupport {
	protected SmartDashboard ba;

	public Smartdevice(String name) throws Exception {
		super(name);
	}

	public Smartdevice(SmartDashboard b) throws Exception {
		super("SmartDashboard");
		this.ba=b;
		this.view = ba.getOutputView();
	}

	@Override
	protected void notifyUserMissionStarted() throws Exception {
//		ba.callNotify("startmission");
		view.addOutput("Received \"Mission Started\" from Drone");
	}

	@Override
	protected void updateGauges(String data) throws Exception {
		String s = Utils.cleanJSONFromContact(data);
		SensorsData sensorsData = Factory.createSensorsData(s);
		Time time = new Time(sensorsData.getTime());
		view.setOutput("");
		view.addOutput("Sensors data updated at "+ time.toString() + "\n");
		for(IGauge g : sensorsData.getGauges()) {
			int t = Utils.getGaugeType(g);
			String type = Utils.getGaugeName(t);
			if (type.equals("Fuelometer"))
				view.addOutput("Fuel: "+g.getCurValRepDisplayed()+ " l ("+ Double.parseDouble(g.getCurValRepDisplayed().toString())/30*100 + "%)\n");
			if (type.equals("Speedometer"))
				view.addOutput("Speed: "+g.getCurValRepDisplayed()+"\n");
			if (type.equals("Odometer"))
				view.addOutput("Odo: "+g.getCurValRepDisplayed()+" km\n");
			if (type.equals("Loctracker")){
				String loc[] = g.getCurValRepDisplayed().split("-");
				view.addOutput("Latitude: "+loc[0].replace("Lat: ", "")+"\n");
				view.addOutput("Longitude: "+loc[1].replace("Lng: ", "")+"\n");
			}
		}
	}

	@Override
	protected void missionFinished() throws Exception {
//		ba.callNotify("endmission");
		view.addOutput("\nReceived \"Mission Ended\" from Drone");
		Thread.sleep(3000);
		view.setOutput("");
	}
}
