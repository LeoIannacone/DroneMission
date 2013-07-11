package it.unibo.contact.droneSubsystem;

public class OdoSensor extends OdoSensorSupport {

	public OdoSensor(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initSensor() throws Exception {
		// TODO Auto-generated method stub
		super.initSensor();
		this.time=DefaultValues.ODOTIME * 1000;
	}
}
