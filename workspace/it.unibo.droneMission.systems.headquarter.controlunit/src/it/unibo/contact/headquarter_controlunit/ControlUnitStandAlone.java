package it.unibo.contact.headquarter_controlunit;

public class ControlUnitStandAlone extends SubSystemHeadQuarterMain {
	
	public static void main(String args[]) throws Exception {
		SubSystemHeadQuarterMain system = new SubSystemHeadQuarterMain( );
		system.doJob();
	}
	
}
