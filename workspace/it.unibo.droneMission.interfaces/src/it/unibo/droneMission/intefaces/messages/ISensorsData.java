package it.unibo.droneMission.intefaces.messages;

import java.util.List;

import it.unibo.droneMission.interfaces.IGauge;

public interface ISensorsData extends IMessage {
	
	// add and get gauges to package
	public void addGauge(IGauge gauge);
	public List<IGauge> getGauges();
	
}
