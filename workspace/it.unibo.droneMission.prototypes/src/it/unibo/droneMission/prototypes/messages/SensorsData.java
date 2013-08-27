package it.unibo.droneMission.prototypes.messages;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.LocTracker;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.gauges.IGauge;
import it.unibo.droneMission.interfaces.messages.ISensorsData;
import it.unibo.droneMission.interfaces.messages.TypesSensor;

public class SensorsData extends Message implements ISensorsData {

	protected List<IGauge> gauges;
	
	public SensorsData() {
		this.gauges = new ArrayList<IGauge>();
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
	public String toJSON() {
		ArrayList<Sensor> list = new ArrayList<Sensor>();
		for(IGauge g : gauges) {
			Sensor s = (Sensor) Utils.fromGaugeToSensor(g);
			list.add(s);
		}
		return (new Gson()).toJson(list);
	}
}
