package it.unibo.droneMission.interfaces.gauges;
/**
 * @model 
 */
public interface ISpeedometer extends IGaugeVariant {
	/**
	 * @model 
	 */
	public static final int INIT = DefaultValues.INITSPEED; //velocit� massima
	/**
	 * @model 
	 */
	public static final int MAX = DefaultValues.MAXSPEED; //velocit� massima
	/**
	 * @model 
	 */
	public static final int MIN = DefaultValues.MINSPEED; //velocit� minima
	/**
	 * @model 
	 */
	public static final int DS = DefaultValues.DS; //variazione di velocit�
}
