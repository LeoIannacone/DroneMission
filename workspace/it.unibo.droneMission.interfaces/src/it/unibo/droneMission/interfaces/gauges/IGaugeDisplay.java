package it.unibo.droneMission.interfaces.gauges;


/**
 * @model 
 */
public interface IGaugeDisplay extends IDisplay{
	/**
	 * @model 
	 */
	public String getDisplayedVal();
	/**
	 * Sets the value of the '{@link it.unibo.droneMission.interfaces.gauges.IGaugeDisplay#getDisplayedVal <em>Displayed Val</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Displayed Val</em>' attribute.
	 * @see #getDisplayedVal()
	 * @generated
	 */
	void setDisplayedVal(String value);
	/**
	 * @model 
	 */
	public void update(IGaugeValue v);
}
