package it.unibo.contact.HeadQuarter;

import it.unibo.droneMission.interfaces.headquarter.IStorage;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.messages.Command;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.Utils;
import it.unibo.droneMission.systems.headquarter.FactoryStorage;

public class Server extends ServerSupport {

	protected IStorage storage;
	
	public Server(String name) throws Exception {
		super(name);
		storage = FactoryStorage.getInstance(FactoryStorage.MYSQL);
	}
	
	@Override
	public void doJob() throws Exception {
		testStartMission();	
	}
	
	private void testStartMission() {
		ICommand c = new Command(TypesCommand.SPEED_SET);
		c.setValue(60);
		forwardCommand(c);
	}
	
	public IReply forwardCommand(ICommand command) {
		String cmd = Utils.adaptJSONToContact(command.toJSON());
		try {
			curAcquireOneReply = hl_server_demand_forwardCommand_controlUnit(cmd);
			curReply = curAcquireOneReply.acquireReply();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curReplyContent = curReply.msgContent();
		String reply =curReplyContent;
		IReply r = Factory.createReply(Utils.cleanJSONFromContact(reply));
		return r;
	}
	
	public IReply forwardCommand(String commandJSON) {
		ICommand c = Factory.createCommand(commandJSON);
		return forwardCommand(c);
	}
/*
	@Override
	protected String showReplyToCommand(String reply) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	protected String getSensorsData(String mission_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPicturePackage(String mission_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNotifies(String mission_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMission(String mission_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

*/
}
