package it.unibo.droneMission.display;


import it.unibo.droneMission.interfaces.gauges.IGaugeDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;

public class DisplayGuiCoords extends DisplayGuiGauge implements IGaugeDisplay{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisplayGuiCoords(IBasicEnvAwt env) {
		super(env);	
	}
}