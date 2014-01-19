package it.unibo.droneMission.interfaces.gauges;

import java.util.Observer;
/**
 * @model
 */
public interface IGauge{
	/**
	 * @model 
	 */
	public void addObserver(Observer o);
	/**
	 * @model 
	 */	
	public void deleteObserver(Observer o); 
	/**
	 * @model
	 */
	public String getCurValRepDisplayed(); //ottengo valore in display 
	/**
	 * @model
	 */
	void setCurValRepDisplayed(String value);
	/**
	 * @model containment="true"
	 */
	public IGaugeValue getVal(); //ottengo valore dal sensore
	/**
	 * @model
	 */
	
	void setVal(IGaugeValue value);
}