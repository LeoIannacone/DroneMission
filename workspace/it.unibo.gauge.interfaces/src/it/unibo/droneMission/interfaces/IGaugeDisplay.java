package it.unibo.droneMission.interfaces;
/**
 * @model 
 */
public interface IGaugeDisplay extends IDisplay{
	/**
	 * @model 
	 */
	public String getDisplayedVal();
	/**
	 * @model 
	 */
	public void update(IGaugeValue v);
}
