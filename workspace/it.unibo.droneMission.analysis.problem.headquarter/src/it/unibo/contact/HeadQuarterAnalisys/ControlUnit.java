package it.unibo.contact.HeadQuarterAnalisys;

public class ControlUnit extends ControlUnitSupport {

	public ControlUnit(String name) throws Exception {
		super(name);
	}

	@Override
	protected void storeDataSensors(String sensorsDatasReceived)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void storePicturePackage(String picturePackageReceived)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkCommandStart() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getWrongStartCommandReply() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean storeCommandAndReply(String c, String r) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean checkEndMission() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void shutdown() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
