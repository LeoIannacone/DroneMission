package it.unibo.droneMission.tests.messages;

import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.interfaces.messages.TypesNotify;
import it.unibo.droneMission.interfaces.messages.TypesReply;
import it.unibo.droneMission.interfaces.messages.TypesSensor;
import it.unibo.droneMission.prototypes.messages.Command;
import it.unibo.droneMission.prototypes.messages.Factory;
import it.unibo.droneMission.prototypes.messages.Notify;
import it.unibo.droneMission.prototypes.messages.Reply;
import it.unibo.droneMission.prototypes.messages.SensorData;
import junit.framework.TestCase;

public class FactoryTest extends TestCase {
	
	public FactoryTest(String arg0){
		super(arg0);
	}
	
	public void testCommandCreationWithValue() {
		int type = TypesCommand.SPEED_SET;
		int value = 60;
		Command c = new Command(type, value);
		
		String json = c.toJSON();
		Command cNew = Factory.createCommand(json);
		
		assertEquals(c.getType(), cNew.getType());
		assertEquals(c.hasValue(), cNew.hasValue());
		assertEquals(c.getValue(), cNew.getValue());
	}
	
	public void testCommandCreationWithNoValue() {
		int type = TypesCommand.SPEED_SET;
		Command c = new Command(type);
		
		String json = c.toJSON();
		Command cNew = Factory.createCommand(json);
		
		assertEquals(c.getType(), cNew.getType());
		assertEquals(c.hasValue(), cNew.hasValue());
	}
	
	public void testReplyCreationWithMessage() {
		int type = TypesReply.REPLY_OK;
		String message = "This is just a test";
		Reply r = new Reply(type, message);
		
		String json = r.toJSON();
		Reply rNew = Factory.createReply(json);
		
		assertEquals(r.getType(), rNew.getType());
		assertEquals(r.hasValue(), rNew.hasValue());
		assertEquals(r.getValue(), rNew.getValue());
	}
	
	public void testReplyCreationWithNoMessage() {
		int type = TypesReply.REPLY_OK;
		Reply r = new Reply(type);
		
		String json = r.toJSON();
		Reply rNew = Factory.createReply(json);
		
		assertEquals(r.getType(), rNew.getType());
		assertEquals(r.getValue(), rNew.getValue());
	}
	
	public void testNotifyCreationWithMessage() {
		int type = TypesNotify.END_MISSION;
		String message = "This is just a test";
		Notify n = new Notify(type, message);
		
		String json = n.toJSON();
		Notify nNew = Factory.createNotify(json);
		
		assertEquals(n.getType(), nNew.getType());
		assertEquals(n.hasValue(), nNew.hasValue());
		assertEquals(n.getValue(), nNew.getValue());
	}
	
	public void testNotifyCreationWithNoMessage() {
		int type = TypesNotify.END_MISSION;
		Notify n = new Notify(type);
		
		String json = n.toJSON();
		Notify nNew = Factory.createNotify(json);
		
		assertEquals(n.getType(), nNew.getType());
		assertEquals(n.getValue(), nNew.getValue());
	}
	
	public void testCreationSensorData() {
		int type = TypesSensor.ODOMETER;
		String message = "This is just a test";
		SensorData s = new SensorData(type, message);
		
		String json = s.toJSON();
		Notify sNew = Factory.createNotify(json);
		
		assertEquals(s.getType(), sNew.getType());
		assertEquals(s.hasValue(), sNew.hasValue());
		assertEquals(s.getValue(), sNew.getValue());
	}
}
