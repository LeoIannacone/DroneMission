package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public interface INotify extends IMessage {
	
	// get notify type
	/**
	 * @model 
	 */
	public int getType();
	
	// message to send/receive
	/**
	 * @model 
	 */
	public void setMessage(String message);
	/**
	 * @model 
	 */
	public String getMessage();
	
}
