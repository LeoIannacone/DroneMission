package it.unibo.contact.UAV;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.interfaces.messages.TypesNotify;
import it.unibo.droneMission.interfaces.messages.TypesReply;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.Notify;
import it.unibo.droneMission.messages.Reply;
import it.unibo.droneMission.messages.Utils;

public class Drone extends DroneSupport {

	protected Fuelometer fuelometer;
	protected LocTracker loctracker;
	protected Odometer odometer;
	protected Speedometer speedometer;
	
	public Drone(String name) throws Exception {
		super(name);
		speedometer = new Speedometer();
		odometer = new Odometer();
		loctracker = new LocTracker();
		fuelometer = new Fuelometer();
	}

	@Override
	protected void startMission() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void endMission() throws Exception {
		// TODO Auto-generated method stub
		
	}

	protected void setSpeed(double value) {
		speedometer.setVal(new GaugeValueDouble(value));
	}
	
	@Override
	protected String handleCommand(String commandJSON) throws Exception {
		IReply reply = new Reply(TypesReply.REPLY_OK);
		ICommand command = Factory.createCommand(Utils.cleanJSONFromContact(commandJSON));
		if (command.getTime() == TypesCommand.SPEED_SET) {
			reply.setValue("Speed set correctly");
		}
		
		else {
			reply.setType(TypesReply.REPLY_FAIL);
			reply.setValue("No command recognized");
		}
		
		return Utils.adaptJSONToContact(reply.toJSON());
	}

	@Override
	protected boolean checkStartMission(String commandJSON) throws Exception {
		ICommand command = Factory.createCommand(Utils.cleanJSONFromContact(commandJSON));
		return command.getTime() == TypesCommand.SPEED_SET;
	}

	@Override
	protected boolean checkEndMission() throws Exception {
		return fuelometer.getVal().valAsDouble() <= 0.5;
	}

	@Override
	protected String getSensorsData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPicturePackage() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNotifyStart() throws Exception {
		INotify n = new Notify(TypesNotify.START_MISSION, "Mission started");
		return Utils.adaptJSONToContact(n.toJSON());
	}

	@Override
	protected String getNotifyEnd() throws Exception {
		INotify n = new Notify(TypesNotify.END_MISSION, "Mission finished");
		return Utils.adaptJSONToContact(n.toJSON());
	}

	@Override
	protected String getWrongCommandStartReply() throws Exception {
		IReply r = new Reply(TypesReply.REPLY_FAIL);
		r.setValue("Wrong start command");
		return Utils.adaptJSONToContact(r.toJSON());
	}


}
