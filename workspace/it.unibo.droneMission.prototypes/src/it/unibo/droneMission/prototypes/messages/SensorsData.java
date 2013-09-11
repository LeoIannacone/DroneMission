package it.unibo.droneMission.prototypes.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

public class SensorsData extends Message implements ISensorsData {

	protected List<IGauge> gauges;
	protected long time;
	
	public SensorsData() {
		this.gauges = new ArrayList<IGauge>();
		this.time = new Date().getTime();
	}
	
	public SensorsData (List<IGauge> gauges) {
		this.gauges = gauges;
	}
	
	@Override
	public void addGauge(IGauge gauge) {
		gauges.add(gauge);
	}

	@Override
	public boolean removeGauge(IGauge gauge) {
		return gauges.remove(gauge);	
	}

	@Override
	public List<IGauge> getGauges() {
		return gauges;
	}

	@Override
	public boolean hasGauge(IGauge gauge) {
		return gauges.contains(gauge);
	}
	
	@Override
	public void setTime(long milliseconds) {
		this.time = milliseconds;		
	}

	@Override
	public long getTime() {
		return this.time;
	}
	
	@Override
	public String toJSON() {
		PrivatePackage privatepackage = new PrivatePackage(gauges, time);
		return (new Gson()).toJson(privatepackage);
	}
	
	// this private class is just a trick to have a faster 
	// and autocreated JSON rapresentation
	private class PrivatePackage {
		private ArrayList<Sensor> sensors;
		private long time;
		
		public PrivatePackage(List<IGauge> gauges, long time) {
			sensors = new ArrayList<Sensor>();
			for(IGauge g : gauges) {
				Sensor s = (Sensor) Utils.fromGaugeToSensor(g);
				sensors.add(s);
			}
			this.time = time;
		}
	}
	
}


