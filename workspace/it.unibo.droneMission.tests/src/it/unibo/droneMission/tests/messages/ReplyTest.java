package it.unibo.droneMission.tests.messages;

import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.prototypes.messages.Reply;
import junit.framework.TestCase;

public class ReplyTest extends TestCase {
	
	protected Reply r;
	
	private String message = "This is a Reply test message";
	private int type = TypesCommand.START_MISSION;  

	public ReplyTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		r = new Reply(type);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

	public void testOnCreate(){
		assertTrue("testOnCreate",(r.getType() == type && r.hasValue() == false));
		r = new Reply(type, message);
		assertTrue("testOnCreate",(r.getType() == type && r.hasValue() == true && r.getValue() == message)); 
	}
	
	public void testNoMessage() {
		r = new Reply(type);
		assertTrue("testNoValue",(r.hasValue() == false));
	}
	
	//Increment 
	public void testSetValue(){
		try {
			r.setValue(message);
			assertTrue("testSetValue",(r.hasValue() && r.getValue() == message));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
}
