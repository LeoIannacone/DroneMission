package it.unibo.droneMission.interfaces.gauges;
/**
 * @model 
 */
public interface IFuelometer extends IGaugeMonotonic {
	/**
	 * @model 
	 */
	public static final double MAX = DefaultValues.MAXFUEL;
	/**
	 * @model 
	 */
	public static final double MIN = DefaultValues.MINFUEL;
}
