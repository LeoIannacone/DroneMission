package it.unibo.contact.headquarter_controlunit;

import it.unibo.baseEnv.basicFrame.EnvFrame;


public class ControlUnitStandAlone extends SubSystemHeadQuarterMain {
	
	public static void main(String args[]) throws Exception {
		SubSystemHeadQuarterMain system = new ControlUnitStandAlone( );
		system.doJob();
		System.setProperty("inputTimeOut", "600000");
	}
	protected void initGui(){
		 env = new EnvFrame( "ControlUnitStandAlone", this );
		 env.init();
		 env.writeOnStatusBar( Thread.currentThread() +  " | ControlUnitStandAlone working ...",14);
		 view = env.getOutputView();
	}		
}
