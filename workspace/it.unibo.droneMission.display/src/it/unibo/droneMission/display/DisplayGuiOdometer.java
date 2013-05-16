package it.unibo.droneMission.display;

import it.unibo.droneMission.interfaces.IGaugeDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;

public class DisplayGuiOdometer extends DisplayGuiGauge implements IGaugeDisplay{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisplayGuiOdometer(IBasicEnvAwt env) {
		super(env);
	}
}