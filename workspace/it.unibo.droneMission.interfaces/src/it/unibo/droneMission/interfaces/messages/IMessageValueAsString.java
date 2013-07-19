package it.unibo.droneMission.interfaces.messages;

public interface IMessageValueAsString extends IMessage {
	/**
	 * @model 
	 */
	public void setMessage(String message);
	
	/**
	 * @model 
	 */
	public String getMessage();
	
	/**
	 * @model 
	 */
	public boolean hasMessage();
}
