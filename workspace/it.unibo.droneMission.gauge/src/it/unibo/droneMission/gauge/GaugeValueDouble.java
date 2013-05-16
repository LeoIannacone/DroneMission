package it.unibo.droneMission.gauge;

import java.text.DecimalFormat;

import it.unibo.droneMission.interfaces.IGaugeValueDouble;

public class GaugeValueDouble implements IGaugeValueDouble {
	
	protected double val;
	
	public GaugeValueDouble(double V){
		val=V;
	}

	public double valAsDouble() {
		// TODO Auto-generated method stub
		return val;
	}

	public int valAsInt() {
		// TODO Auto-generated method stub
		return (int)val;
	}

	public String valAsString() {
		// TODO Auto-generated method stub
		return ""+val;
	}

	public void set(double V) {
		// TODO Auto-generated method stub
		val = V;
	}

}
