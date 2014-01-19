package it.unibo.droneMission.interfaces.messages;
/**
 * @model 
 */
public interface IMessageValueAsString extends IMessageValue {
	/**
	 * @model 
	 */
	public void setValue(String value);
	
	/**
	 * @model 
	 */
	public String getValue();
}
