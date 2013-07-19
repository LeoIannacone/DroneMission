package it.unibo.droneMission.interfaces.messages;
/**
 * @model 
 */
public interface IMessageValue extends IMessage {
	/**
	 * @model 
	 */
	public boolean hasValue();
	/**
	 * @model 
	 */
	public void deleteValue();
}
