package it.unibo.droneMission.tests.messages;

import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.messages.Command;
import it.unibo.droneMission.messages.MessageTypeAsIntValueAsInt;
import junit.framework.TestCase;

public class MessageTypeAsIntValueAsIntTest extends TestCase {
	
	protected MessageTypeAsIntValueAsInt m;
	
	private int value = 3;
	private int newValue = 5;
	private int type = 10;
	private int newType = 30;

	public MessageTypeAsIntValueAsIntTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		m = new MessageTypeAsIntValueAsInt(type);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

	public void testOnCreate(){
		m = new MessageTypeAsIntValueAsInt(type);
		assertTrue("testOnCreate only type",(m.getType() == type && m.hasValue() == false));
		// create with value
		m = new MessageTypeAsIntValueAsInt(type, value);
		assertTrue("testOnCreate type and value",(m.getType() == type && m.hasValue() == true && m.getValue() == value));
	}
	
	public void testSetType() {
		m = new MessageTypeAsIntValueAsInt(type);
		assertTrue("testSetType old",(m.getType() == type && m.hasValue() == false));
		m.setType(newType);
		assertTrue("testSetType new",(m.getType() == newType && m.hasValue() == false));
	}
	
	public void testNoValue() {
		m = new MessageTypeAsIntValueAsInt(type);
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
