package it.unibo.droneMission.interfaces;

import java.util.Observer; /**
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
	public String getCurValRepDisplayed(); //trasformazione valore in stringa 
	/**
	 * @model containment="true"
	 */
	public IGaugeValue getVal(); //ottengo valore dal sensore
}