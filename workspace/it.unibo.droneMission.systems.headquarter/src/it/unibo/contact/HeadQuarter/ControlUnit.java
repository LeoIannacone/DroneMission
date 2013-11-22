package it.unibo.contact.HeadQuarter;

import java.util.List;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.headquarter.IStorage;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.interfaces.messages.TypesReply;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.Reply;
import it.unibo.droneMission.messages.Utils;
import it.unibo.droneMission.systems.headquarter.FactoryStorage;

public class ControlUnit extends ControlUnitSupport {

	private IStorage storage;
	private double fuelLevel;
	
	public ControlUnit(String name) throws Exception {
		super(name);
		fuelLevel = Fuelometer.MAX;
		storage = FactoryStorage.getInstance(FactoryStorage.MYSQL);
		//storage.setDebug(3);
	}

	private void setFuelLevelFromGauges(ISensorsData s) {
		List<IGauge> gauges = s.getGauges();
		for (IGauge g : gauges) {
			if (g.getClass() == Fuelometer.class) {
				fuelLevel = g.getVal().valAsDouble();
				break;
			}
		}
	}
	
	@Override
	protected void storeDataSensors(String sensorsDatasReceived)
			throws Exception {
		sensorsDatasReceived = Utils.cleanJSONFromContact(sensorsDatasReceived);
		ISensorsData s = Factory.createSensorsData(sensorsDatasReceived);
		setFuelLevelFromGauges(s);
		storage.storeSensorsData(s);		
	}

	@Override
	protected void storePicturePackage(String picturePackageReceived)
			throws Exception {
		picturePackageReceived = Utils.cleanJSONFromContact(picturePackageReceived);
		IPicturePackage p = Factory.createPicturePackage(picturePackageReceived);
		storage.storePicturePackage(p);
	}

	@Override
	protected boolean checkCommandStart(String command) throws Exception {
		command = Utils.cleanJSONFromContact(command);
		ICommand c = Factory.createCommand(command);
		return c.getType() == TypesCommand.START_MISSION || c.getType() == TypesCommand.SPEED_SET;
	}

	@Override
	protected String getWrongStartCommandReply() throws Exception {
		Reply r = new Reply(TypesReply.REPLY_FAIL);
		r.setValue("Wrong start mission command");
		return Utils.adaptJSONToContact(r.toJSON());
	}

	@Override
	protected void storeCommandAndReply(String c, String r) throws Exception {
		c = Utils.cleanJSONFromContact(c);
		r = Utils.cleanJSONFromContact(r);
		ICommand command = Factory.createCommand(c);
		IReply reply = Factory.createReply(r);
		storage.storeCommandAndReply(command, reply);
	}

	@Override
	protected boolean checkEndMission() throws Exception {
		return fuelLevel <= Fuelometer.MIN;
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
