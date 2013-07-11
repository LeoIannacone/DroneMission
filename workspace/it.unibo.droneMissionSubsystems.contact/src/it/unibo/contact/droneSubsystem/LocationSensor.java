package it.unibo.contact.droneSubsystem;

public class LocationSensor extends LocationSensorSupport {

	public LocationSensor(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initSensor() throws Exception {
		// TODO Auto-generated method stub
		super.initSensor();
		this.time=DefaultValues.LOCTIME * 1000;
	}
}
