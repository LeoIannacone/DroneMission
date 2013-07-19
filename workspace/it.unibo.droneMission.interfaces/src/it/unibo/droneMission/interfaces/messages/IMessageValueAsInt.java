package it.unibo.droneMission.interfaces.messages;
/**
 * @model 
 */
public interface IMessageValueAsInt extends IMessage {
	/**
	 * @model 
	 */
	public boolean hasValue();
	/**
	 * @model 
	 */
	public int getValue();
	/**
	 * @model 
	 */
	public void setValue(int value);
}
