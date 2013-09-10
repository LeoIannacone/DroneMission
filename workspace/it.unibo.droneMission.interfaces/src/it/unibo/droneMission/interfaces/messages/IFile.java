package it.unibo.droneMission.interfaces.messages;

import java.io.InputStream;
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
	/**
	 * @model 
	 */
	public void setLastModificationTime(long time);
	/**
	 * @model 
	 */
	public long getLastModificationTime();
	
	// data as InputStream
	/**
	 * @model 
	 */
	public void setData(InputStream file);
	/**
	 * @model 
	 */
	public InputStream getData();
	
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
