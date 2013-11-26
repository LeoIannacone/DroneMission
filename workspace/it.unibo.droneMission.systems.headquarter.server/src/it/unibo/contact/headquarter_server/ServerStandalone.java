package it.unibo.contact.headquarter_server;

public class ServerStandalone extends Headquarter_serverMain {

	public void doJob(){
		initProperty();
		init();
		configure();
		//start();
	}
	
	public ServerSupport getServer() {
		return server;
	}
	
}
