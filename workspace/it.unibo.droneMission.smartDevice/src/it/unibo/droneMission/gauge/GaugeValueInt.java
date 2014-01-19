package it.unibo.droneMission.gauge;

import it.unibo.droneMission.interfaces.gauges.IGaugeValueInt;

public class GaugeValueInt implements IGaugeValueInt{
	
	protected int val;
	
	public GaugeValueInt(int V){
		val=V;
	}

	public double valAsDouble() {
		// TODO Auto-generated method stub
		return (double)val;
	}

	public int valAsInt() {
		// TODO Auto-generated method stub
		return val;
	}

	public String valAsString() {
		// TODO Auto-generated method stub
		return ""+val;
	}

	public void set(int V) {
		// TODO Auto-generated method stub
		val = V;
	}

}
