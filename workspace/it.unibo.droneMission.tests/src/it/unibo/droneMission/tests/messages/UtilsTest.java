package it.unibo.droneMission.tests.messages;	

import java.io.UnsupportedEncodingException;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.messages.Sensor;
import it.unibo.droneMission.messages.Utils;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.messages.ISensor;
import it.unibo.droneMission.interfaces.messages.TypesSensor;
import junit.framework.TestCase;

public class UtilsTest extends TestCase {
	
	public static void testFromGaugeToSensor()
	{
		Speedometer speedometer = new Speedometer();
		speedometer.setVal(new GaugeValueInt(60));
		ISensor speedsensor = Utils.fromGaugeToSensor(speedometer);
		
		
		assertEquals(speedsensor.getType(), TypesSensor.SPEEDOMETER);
		assertEquals(speedsensor.getValue(),""+speedometer.getVal().valAsDouble());
		assertTrue(speedsensor.hasValue());
		
		Fuelometer fuelometer = new Fuelometer();
		fuelometer.setVal(new GaugeValueDouble(12.3));
		ISensor fuelsensor = Utils.fromGaugeToSensor(fuelometer);
		
		assertEquals(fuelsensor.getType(), TypesSensor.FUELOMETER);
		assertEquals(fuelsensor.getValue(),""+fuelometer.getVal().valAsDouble());
		assertTrue(fuelsensor.hasValue());
		
		Odometer o = new Odometer();
		o.setVal(new GaugeValueInt(3));
		ISensor contakm = Utils.fromGaugeToSensor(o);
		
		assertEquals(contakm.getType(), TypesSensor.ODOMETER);
		assertEquals(contakm.getValue(),""+o.getVal().valAsDouble());
		assertTrue(contakm.hasValue());

		
		GaugeValueDouble latitudine=new GaugeValueDouble(0);
		GaugeValueDouble longitudine=new GaugeValueDouble(0);	
		latitudine.set(60.2);
		longitudine.set(45.3);
		LocTracker loctracker=new LocTracker(latitudine,longitudine);
		
		ISensor position = Utils.fromGaugeToSensor(loctracker);
				
		
		assertEquals(position.getType(),TypesSensor.LOCTRACKER);
		assertEquals(position.getValue(),loctracker.toString());
		assertTrue(position.hasValue());
		

	}
	
	public static void testFromSensorToGauge()
	{
		Sensor s =new Sensor(TypesSensor.SPEEDOMETER,"60");
		IGauge speedometer =Utils.fromSensorToGauge(s);
		assertEquals(speedometer.getClass(),Speedometer.class);
		assertEquals(""+speedometer.getVal().valAsInt(),s.getValue());
		
		
		s = new Sensor(TypesSensor.ODOMETER,"40");
		IGauge odometer = Utils.fromSensorToGauge(s);
		assertEquals(odometer.getClass(), Odometer.class);
		assertEquals(""+odometer.getVal().valAsInt(),s.getValue());
		
		
		s= new Sensor(TypesSensor.FUELOMETER,"96.2");
		IGauge fuelometer = Utils.fromSensorToGauge(s);
		assertEquals(fuelometer.getClass(), Fuelometer.class);
		assertEquals(""+fuelometer.getVal().valAsDouble(),s.getValue());
		
		s = new Sensor(TypesSensor.LOCTRACKER,"45.2;36.8");
		IGauge loctracker = Utils.fromSensorToGauge(s);
		assertEquals(loctracker.getClass(),LocTracker.class);
		assertEquals(""+loctracker.toString(),s.getValue());
	}
	
	
	public static void testDecodeFile() {
		String clear = "hello world";  
		String base64 = "aGVsbG8gd29ybGQK";
		
		String test = null;
		try {
			test = new String(Utils.decodeFileFromBase64(base64), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (test.charAt(test.length()-1) == '\n')
			clear += '\n'; // needed \n - base64 decode/encode add newline automatically
		assertEquals(clear, test);
	}
	
	/*
	 * Hard to test - tested with images in MessageTest
	 * 
	public static void testEncodeFile() {
		String clear = "hello world";  
		String base64 = "aGVsbG8gd29ybGQK"; // needed \n - base64 decode/encode add them automatically
		
		java.io.File temp = null;
		try {
			temp = java.io.File.createTempFile("tempfile", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		    bw.write(clear);
		    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String test = Utils.encodeFileToBase64(temp);
//		if (test.charAt(test.length()-1) == '\n')
//			base64 += '\n';
		assertEquals(base64, test);
	}
	*/
	
	public static void testCleanString(){
		String dataIn="string to clean";
		String toClean="\' "+dataIn+" \'";
		assertEquals(Utils.cleanJSONString(toClean), dataIn);
	}
}