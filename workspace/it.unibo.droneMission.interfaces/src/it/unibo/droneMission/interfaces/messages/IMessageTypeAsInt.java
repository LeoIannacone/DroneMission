package it.unibo.droneMission.interfaces.messages;

public interface IMessageTypeAsInt extends IMessage {
	/**
	 * @model 
	 */
	public int getType();
	
	/**
	 * @model 
	 */
	public void setType(int type);
	
}
