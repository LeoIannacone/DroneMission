package it.unibo.contact.UAV;

public class DroneStandAlone extends SubSystemDroneMain {
	
	public static void main(String args[]) throws Exception {
		SubSystemDroneMain system = new SubSystemDroneMain( );
		system.doJob();
		System.setProperty("inputTimeOut", "600000");
	}
}
