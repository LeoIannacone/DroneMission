package it.unibo.droneMission.interfaces.gauges;

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
	 * Sets the value of the '{@link it.unibo.droneMission.interfaces.gauges.IDisplay#getCurVal <em>Cur Val</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cur Val</em>' attribute.
	 * @see #getCurVal()
	 * @generated
	 */
	void setCurVal(String value);
	/**
	 * @model 
	 */
	public void update(String val);
}
