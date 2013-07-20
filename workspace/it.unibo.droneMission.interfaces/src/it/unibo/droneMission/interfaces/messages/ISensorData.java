package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public interface ISensorData extends IMessageValueAsString, IMessageTypeAsInt {
	/**
	 * @model 
	 */
	public void setValueType(int valueType);
	/**
	 * @model 
	 */
	public int getValueType();
	
}
