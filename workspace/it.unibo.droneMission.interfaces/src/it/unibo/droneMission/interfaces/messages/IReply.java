package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public interface IReply extends IMessage {

	/**
	 * @model 
	 */
	public int getType();
	
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
