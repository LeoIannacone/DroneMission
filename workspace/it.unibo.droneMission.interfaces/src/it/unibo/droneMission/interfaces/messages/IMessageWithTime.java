package it.unibo.droneMission.interfaces.messages;
/**
 * @model 
 */
public interface IMessageWithTime extends IMessage {
	/**
	 * @model 
	 */
	public long getTime();
	
	/**
	 * @model 
	 */
	public void setTime(long time);
}
