package it.unibo.droneMission.tests.messages;

import java.util.Date;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.interfaces.messages.TypesNotify;
import it.unibo.droneMission.interfaces.messages.TypesReply;
import it.unibo.droneMission.interfaces.messages.TypesSensor;
import it.unibo.droneMission.messages.Command;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.File;
import it.unibo.droneMission.messages.Notify;
import it.unibo.droneMission.messages.PicturePackage;
import it.unibo.droneMission.messages.Reply;
import it.unibo.droneMission.messages.Sensor;
import it.unibo.droneMission.messages.SensorsData;
import junit.framework.TestCase;

public class FactoryTest extends TestCase {
	
	public FactoryTest(String arg0){
		super(arg0);
	}
	
	public void testCommandCreationWithValue() {
		int type = TypesCommand.SPEED_SET;
		int value = 60;
		long time = new Date().getTime();
		Command c = new Command(type, value, time);
		
		String json = c.toJSON();
		Command cNew = Factory.createCommand(json);
		
		assertEquals(c.getType(), cNew.getType());
		assertEquals(c.hasValue(), cNew.hasValue());
		assertEquals(c.getValue(), cNew.getValue());
		assertEquals(c.getTime(), cNew.getTime());
	}
	
	public void testCommandCreationWithNoValue() {
		int type = TypesCommand.SPEED_SET;
		Command c = new Command(type);
		long time = c.getTime();
		
		String json = c.toJSON();
		Command cNew = Factory.createCommand(json);
		
		assertEquals(c.getType(), cNew.getType());
		assertEquals(c.hasValue(), cNew.hasValue());
		assertEquals(c.getTime(), cNew.getTime());
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
		assertEquals(r.getTime(), rNew.getTime());
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
	public void testCreationSensorsData()
	{
		SensorsData sensors = new SensorsData();
		
		Odometer o = new Odometer();
		o.setVal(new GaugeValueInt(3));
		sensors.addGauge(o);
		
		Fuelometer f = new Fuelometer();
		f.setVal(new GaugeValueDouble(12.3));
		sensors.addGauge(f);
		
		Speedometer s = new Speedometer();
		s.setVal(new GaugeValueInt(130));
		sensors.addGauge(s);
		
		String json = sensors.toJSON();
		SensorsData s2 = Factory.createSensorsData(json);
		
		for(int i=0;i<sensors.getGauges().size();i++)
		{
			assertEquals(sensors.getGauges().get(i).getVal().valAsString(),
					     s2.getGauges().get(i).getVal().valAsString());
		}
		assertEquals(sensors.getTime(), s2.getTime());
			
	}
	
	public void testCreationFile() {
		String name = "file name";
		String data = "aGVsbG8gd29ybGQK"; // $ echo "hello world"| base64 
		long time = 1379080909;
		
		File f = new File();
		f.setName(name);
		f.setData(data);
		f.setCreationTime(time);
		
		String json = f.toJSON();
		File fNew = Factory.createFile(json);
		
		assertEquals(f.getName(), fNew.getName());
		assertEquals(f.getDataAsBase64(), fNew.getDataAsBase64());
		assertEquals(f.getCreationTime(), fNew.getCreationTime());
	}
	
	
	public void testCreationPicturePackage() {
		// create File
		String name = "file name";
		String data = "aGVsbG8gd29ybGQK"; // $ echo "hello world"| base64 
		long time = 1379080909;
		File f = new File();
		f.setName(name);
		f.setData(data);
		f.setCreationTime(time);
		
		// Create sensors data
		SensorsData s = new SensorsData();
		Odometer o = new Odometer();
		o.setVal(new GaugeValueInt(3));
		s.addGauge(o);
		Fuelometer fu = new Fuelometer();
		fu.setVal(new GaugeValueDouble(12.3));
		s.addGauge(fu);
		Speedometer sp = new Speedometer();
		sp.setVal(new GaugeValueInt(130));
		s.addGauge(sp);
		
		
		// Create picture package
		PicturePackage pack = new PicturePackage(s, f);
		
		String json = pack.toJSON();
		PicturePackage packNew = Factory.createPicturePackage(json);
		
		SensorsData s2 = (SensorsData) packNew.getSensorsData();
		
		for(int i=0;i<s.getGauges().size();i++)
		{
			assertEquals(s.getGauges().get(i).getVal().valAsString(),
					     s2.getGauges().get(i).getVal().valAsString());
		}
		assertEquals(s.getTime(), s2.getTime());
		
		
		File fNew = (File) packNew.getFile();
		
		assertEquals(f.getName(), fNew.getName());
		assertEquals(f.getDataAsBase64(), fNew.getDataAsBase64());
		assertEquals(f.getCreationTime(), fNew.getCreationTime());
	}
}
