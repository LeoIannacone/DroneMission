package it.unibo.contact.droneSubsystem;

import it.unibo.contact.droneSubsystem.CameraSupport;

public class Camera extends CameraSupport {

	public Camera(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initCamera() throws Exception {
		// TODO Auto-generated method stub
		sleepTime = DefaultValues.CAMERASLEEP * 1000; 
	}

	@Override
	protected String takePhoto() throws Exception {
		// TODO Auto-generated method stub
		return "photo taken";
	}

}
