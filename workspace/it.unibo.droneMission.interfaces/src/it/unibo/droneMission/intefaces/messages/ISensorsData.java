package it.unibo.droneMission.intefaces.messages;

import java.util.List;

import it.unibo.droneMission.interfaces.gauges.IGauge;

public interface ISensorsData extends IMessage {
	
	// add, remove and get gauges to package
	public void addGauge(IGauge gauge);
	public boolean removeGauge(IGauge gauge);
	public List<IGauge> getGauges();
	
	// check if gauge is already included
	public boolean hasGauge(IGauge gauge);
	
}
