package it.unibo.droneMission.interfaces.gauges;
/**
 * @model 
 */
public interface ILocTracker extends IGaugeVariant{
	/**
	 * @model 
	 */
	public static final double MAX=DefaultValues.MAXLOC;	
	/**
	 * @model 
	 */
	public static final double MIN=DefaultValues.MINLOC;
	/**
	 * @model 
	 */	
	public static final double INITLAT = DefaultValues.INITLAT;
	/**
	 * @model 
	 */	
	public static final double INITLON = DefaultValues.INITLON;
	/**
	 * @model 
	 */
	void update(IGaugeValue lat, IGaugeValue lon);
	/**
	 * @model 
	 */
	public IGaugeValue getLon();
	/**
	 * @model 
	 */
	public IGaugeValue getLat();
}
