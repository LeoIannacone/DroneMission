package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public interface ICommand extends IMessage {
	
	// return command type
	/**
	 * @model 
	 */
	public int getType();
	
	// command value
	
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
