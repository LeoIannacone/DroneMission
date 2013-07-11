package it.unibo.contact.droneSubsystem;

import it.unibo.contact.droneSubsystem.FuelSensorSupport;

public class FuelSensor extends FuelSensorSupport {

	public FuelSensor(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initSensor() throws Exception {
		// TODO Auto-generated method stub
		super.initSensor();
		this.time=DefaultValues.FUELTIME * 1000;
	}
}
