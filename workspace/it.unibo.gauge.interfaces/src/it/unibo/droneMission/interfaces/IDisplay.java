package it.unibo.droneMission.interfaces;

import java.util.Observer;

/**
 * @model 
 */
public interface IDisplay extends Observer{
	/**
	 * @model 
	 */
	public String getCurVal();
	/**
	 * @model 
	 */
	public void update(String val);
}
