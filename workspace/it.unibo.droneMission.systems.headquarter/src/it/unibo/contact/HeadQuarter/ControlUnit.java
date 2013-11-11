package it.unibo.contact.HeadQuarter;

import it.unibo.droneMission.interfaces.headquarter.IStorage;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.interfaces.messages.TypesReply;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.Reply;
import it.unibo.droneMission.systems.headquarter.FactoryStorage;

public class ControlUnit extends ControlUnitSupport {

	private IStorage storage;
	
	public ControlUnit(String name) throws Exception {
		super(name);
		storage = FactoryStorage.getInstance(FactoryStorage.MYSQL);
	}

	@Override
	protected void storeDataSensors(String sensorsDatasReceived)
			throws Exception {
		ISensorsData s = Factory.createSensorsData(sensorsDatasReceived);
		storage.storeSensorsData(s);		
	}

	@Override
	protected void storePicturePackage(String picturePackageReceived)
			throws Exception {
		IPicturePackage p = Factory.createPicturePackage(picturePackageReceived);
		storage.storePicturePackage(p);
	}

	@Override
	protected boolean checkCommandStart(String command) throws Exception {
		ICommand c = Factory.createCommand(command);
		return c.getType() == TypesCommand.START_MISSION || c.getType() == TypesCommand.SPEED_SET;
	}

	@Override
	protected String getWrongStartCommandReply() throws Exception {
		Reply r = new Reply(TypesReply.REPLY_FAIL);
		r.setValue("Wrong start mission command");
		return r.toJSON();
	}

	@Override
	protected void storeCommandAndReply(String c, String r) throws Exception {
		ICommand command = Factory.createCommand(c);
		IReply reply = Factory.createReply(r);
		storage.storeCommandAndReply(command, reply);
	}

	@Override
	protected boolean checkEndMission() throws Exception {
		// TODO: Implementare - vedere specifiche
		return false;
	}

	@Override
	protected void shutdown() throws Exception {
		if (storage.isOnMission())
			storage.endMission();
	}

	@Override
	protected void storeMissionStarted() throws Exception {
		storage.startMission();
	}
}
