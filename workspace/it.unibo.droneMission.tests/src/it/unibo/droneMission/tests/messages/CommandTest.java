package it.unibo.droneMission.tests.messages;

import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.prototypes.messages.Command;
import junit.framework.TestCase;

public class CommandTest extends TestCase {
	
	protected Command c;
	
	private int value = 3;
	private int type = TypesCommand.START_MISSION;  

	public CommandTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		c = new Command(type);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

	public void testOnCreate(){
		assertTrue("testOnCreate",(c.getType() == type && c.hasValue() == false));
		// create with value
		c = new Command(type, value);
		assertTrue("testOnCreate",(c.getType() == type && c.hasValue() == true && c.getValue() == value));
	}
	
	public void testNoValue() {
		c = new Command(type);
		assertTrue("testNoValue",(c.hasValue() == false));
	}
	
	//Increment 
	public void testSetValue(){
		try {
			c.setValue(value);
			assertTrue("testSetValue",(c.hasValue() && c.getValue() == value));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
}
