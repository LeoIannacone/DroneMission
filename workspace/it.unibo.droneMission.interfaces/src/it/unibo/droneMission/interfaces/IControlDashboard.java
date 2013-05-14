package it.unibo.droneMission.interfaces;


/**
 * @model 
 */
public interface IControlDashboard {
	/**
	 * @model 
	 */
	public void startMission();
	/**
	 * @model 
	 */
	public void stopMission();
	/**
	 * @model 
	 */
	public void setSpeed(int s);
	/**
	 * @model 
	 */
	public void incSpeed();
	/**
	 * @model 
	 */
	public void decSpeed();
}
