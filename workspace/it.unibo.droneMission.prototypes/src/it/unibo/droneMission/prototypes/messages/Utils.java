package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.messages.ISensor;
import it.unibo.droneMission.interfaces.messages.TypesSensor;

public class Utils {
	
	public static final String LOCTRACKER_VALUE_SEPARATOR = ";";
	
	public static ISensor fromGaugeToSensor(IGauge gauge) {
		int type = -1;
		Class<?> cls = gauge.getClass();
		
		String value = "" + gauge.getVal().valAsDouble();
		
		if (cls == Odometer.class)
			type = TypesSensor.ODOMETER;
		
		else if (cls == Speedometer.class)
			type = TypesSensor.SPEEDOMETER;
		
		else if (cls == Fuelometer.class)
			type = TypesSensor.FUELOMETER;
	
		else if (cls == LocTracker.class) {
			type = TypesSensor.LOCTRACKER;
			LocTracker locTracker = (LocTracker) gauge;
			value = String.format("%s%s%s", 
					locTracker.getLat().valAsDouble(),
					LOCTRACKER_VALUE_SEPARATOR,
					locTracker.getLon().valAsDouble()
					);
		}

		return new Sensor(type, value);
	}
	
	public static IGauge fromSensorToGauge(ISensor sensor) {
		int type = sensor.getType();
		
		IGauge g = null;
		
		if (type == TypesSensor.LOCTRACKER) 
		{
			String[] info = sensor.getValue().split(LOCTRACKER_VALUE_SEPARATOR);
			GaugeValueDouble latitude = new GaugeValueDouble(Double.parseDouble(info[0]));
			GaugeValueDouble longitude = new GaugeValueDouble(Double.parseDouble(info[1]));
			g = new LocTracker(latitude, longitude);;
		}
		
		else {
			GaugeValueInt valueInt = new GaugeValueInt((int) Double.parseDouble(sensor.getValue()));
			GaugeValueDouble valueDouble = new GaugeValueDouble(Double.parseDouble(sensor.getValue()));
			
			if (type == TypesSensor.FUELOMETER) {
				g = new Fuelometer();
				g.setVal(valueDouble);
			}
			
			else if (type == TypesSensor.ODOMETER) {
				g = new Odometer();
				g.setVal(valueInt);
			}
			
			else if (type == TypesSensor.SPEEDOMETER) {
				g = new Speedometer();
				g.setVal(valueInt);
			}
		}
		
		return g;
	}
}
