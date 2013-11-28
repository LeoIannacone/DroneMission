package it.unibo.contact.headquarter_server;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.messages.Command;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.Utils;


public class ServerStandAlone extends SubSystemHeadQuarterMain {
	
	public static void main(String args[]) throws Exception {
		ServerStandAlone system = new ServerStandAlone( );
		testMission(system);
	}
	
	public ServerStandAlone() {
		super();
		initProperty();
		init();
		configure();
	}
	
	protected void initGui(){
		 env = new EnvFrame( "ServerStandAlone", this );
		 env.init();
		 env.writeOnStatusBar( Thread.currentThread() +  " | ServerStandAlone working ...",14);
		 view = env.getOutputView();
	}
	
	public void doJob() {
		
	}
	
	private static void testMission(ServerStandAlone system) {
		try {
//			env.println("Send first start");
			system.testSendCommand(60);	
			Thread.sleep(3000);
//			env.println("Send second command");
			system.testSendCommand(100);	
			Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void testSendCommand(int value) {
		ICommand c = new Command(TypesCommand.SPEED_SET);
		c.setValue(value);
		forwardCommand(c);
	}
	
	public IReply forwardCommand(ICommand command) {
		String cmd = Utils.adaptJSONToContact(command.toJSON());
		try {
			server.curAcquireOneReply = server.hl_server_demand_forwardCommand_controlUnit(cmd);
			server.curReply = server.curAcquireOneReply.acquireReply();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.curReplyContent = server.curReply.msgContent();
		String reply = server.curReplyContent;
		IReply r = Factory.createReply(Utils.cleanJSONFromContact(reply));
		return r;
	}
	
	public IReply forwardCommand(String commandJSON) {
		ICommand c = Factory.createCommand(commandJSON);
		return forwardCommand(c);
	}
}
