package it.unibo.droneMission.tests.headquarter;

import it.unibo.contact.headquarter_server.ServerStandAlone;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerStandAlone s = new ServerStandAlone();
		s.doJob();
		s.testSendCommand(60);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.testSendCommand(120);
	}

}
