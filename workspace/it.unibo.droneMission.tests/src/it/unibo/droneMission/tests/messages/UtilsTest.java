package it.unibo.droneMission.tests.messages;	

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.prototypes.messages.Utils;
import it.unibo.droneMission.prototypes.messages.Sensor;
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
				
		System.out.println(position.getValue());
		assertEquals(position.getType(),TypesSensor.LOCTRACKER);
		assertEquals(position.getValue(),loctracker.toString());
		assertTrue(position.hasValue());
		

	}
	
	public static void testFromSensorToGauge()
	{
		IGauge speedometer =Utils.fromSensorToGauge(new Sensor(TypesSensor.SPEEDOMETER,"60"));
		assertEquals(speedometer.getVal().valAsInt(),60);
		
	}
	
	
}
