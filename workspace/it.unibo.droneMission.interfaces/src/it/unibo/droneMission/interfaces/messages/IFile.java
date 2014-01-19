package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public interface IFile extends IMessage {
	
	// file name
	/**
	 * @model 
	 */
	public void setName(String name);
	/**
	 * @model 
	 */
	public String getName();
	
	// file times
	/**
	 * @model 
	 */
	public void setCreationTime(long time);
	/**
	 * @model 
	 */
	public long getCreationTime();
	
	// data as base64 string
	/**
	 * @model 
	 */
	public void setData(String base64);
	/**
	 * @model 
	 */
	public String getDataAsBase64();
	
}