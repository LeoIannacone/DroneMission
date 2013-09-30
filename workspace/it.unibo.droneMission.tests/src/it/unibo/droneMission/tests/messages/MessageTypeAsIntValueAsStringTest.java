package it.unibo.droneMission.tests.messages;

import it.unibo.droneMission.messages.MessageTypeAsIntValueAsString;
import junit.framework.TestCase;

public class MessageTypeAsIntValueAsStringTest extends TestCase {
	
	protected MessageTypeAsIntValueAsString m;
	
	private String value = "This is just a test value";
	private String newValue = "This is a new value test";
	private int type = 10;
	private int newType = 30;

	public MessageTypeAsIntValueAsStringTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		m = new MessageTypeAsIntValueAsString(type);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

	public void testOnCreate(){
		m = new MessageTypeAsIntValueAsString(type);
		assertTrue("testOnCreate only type",(m.getType() == type && m.hasValue() == false));
		// create with value
		m = new MessageTypeAsIntValueAsString(type, value);
		assertTrue("testOnCreate type and value",(m.getType() == type && m.hasValue() == true && m.getValue() == value));
	}
	
	public void testSetType() {
		m = new MessageTypeAsIntValueAsString(type);
		assertTrue("testSetType old",(m.getType() == type && m.hasValue() == false));
		m.setType(newType);
		assertTrue("testSetType new",(m.getType() == newType && m.hasValue() == false));
	}
	
	public void testNoValue() {
		m = new MessageTypeAsIntValueAsString(type);
		assertTrue("testNoValue",(m.hasValue() == false));
	}
	
	//Increment 
	public void testSetValue(){
		m.setValue(value);
		assertTrue("testSetValue old",(m.hasValue() && m.getValue() == value));
		m.setValue(newValue);
		assertTrue("testSetValue new",(m.hasValue() && m.getValue() == newValue));
		
	}
	
	public void testDeleteValue() {
		m.deleteValue();
		assertTrue("testDeleteValue",(m.hasValue() == false));
	}
}
