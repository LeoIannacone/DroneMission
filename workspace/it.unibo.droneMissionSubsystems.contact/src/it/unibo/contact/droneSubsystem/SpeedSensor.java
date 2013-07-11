package it.unibo.contact.droneSubsystem;

public class SpeedSensor extends SpeedSensorSupport {

	public SpeedSensor(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initSensor() throws Exception {
		// TODO Auto-generated method stub
		super.initSensor();
		this.time=DefaultValues.SPEEDTIME * 1000;
	}
}
