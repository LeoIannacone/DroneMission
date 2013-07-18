package it.unibo.droneMission.interfaces.messages;

import java.io.InputStream;

public interface IFile extends IMessage {
	
	// file name
	public void setName(String name);
	public String getName();
	

	// file times
	public void setCreationTime(long time);
	public long getCreationTime();
	public void setLastModificationTime(long time);
	public long getLastModificationTime();
	
	// data as InputStream
	public void setData(InputStream file);
	public InputStream getData();
	
	// data as base64 string
	public void setData(String base64);
	public String getDataAsBase64();
	
}
