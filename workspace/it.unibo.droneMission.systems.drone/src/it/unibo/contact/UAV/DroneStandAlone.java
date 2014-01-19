package it.unibo.contact.UAV;

import it.unibo.baseEnv.basicFrame.EnvFrame;

public class DroneStandAlone extends SubSystemDroneMain {
	
	public static void main(String args[]) throws Exception {
		SubSystemDroneMain system = new SubSystemDroneMain( );
		system.doJob();
		System.setProperty("inputTimeOut", "600000");
	}
	
	protected void initGui(){
		 env = new EnvFrame( "DroneStandAlone", this );
		 env.init();
		 env.writeOnStatusBar( Thread.currentThread() +  " | ControlUnitStandAlone working ...",14);
		 view = env.getOutputView();
	}	
}
