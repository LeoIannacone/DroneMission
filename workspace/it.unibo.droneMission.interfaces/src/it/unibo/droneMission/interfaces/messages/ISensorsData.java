package it.unibo.droneMission.interfaces.messages;

import java.util.List;

import it.unibo.droneMission.interfaces.gauges.IGauge;

/**
 * @model 
 */
public interface ISensorsData extends IMessage, IMessageWithTime {
	
	// add, remove and get gauges to package
	/**
	 * @model 
	 */
	public void addGauge(IGauge gauge);
	/**
	 * @model 
	 */
	public boolean removeGauge(IGauge gauge);
	/**
	 * @model 
	 */
	public List<IGauge> getGauges();
	
	// check if gauge is already included
	/**
	 * @model 
	 */
	public boolean hasGauge(IGauge gauge);	
	
}
