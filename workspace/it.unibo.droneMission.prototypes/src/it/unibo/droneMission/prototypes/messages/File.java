package it.unibo.droneMission.prototypes.messages;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import it.unibo.droneMission.interfaces.messages.IFile;

public class File extends Message implements IFile{

	protected String name;
	protected String data;
	protected long creationTime; 
	
	public File(java.io.File file)
	{	
		try {
			// get file name
			this.name= file.getName();
			// get creation time
			BasicFileAttributes attributes = Files.readAttributes(Paths.get(file.getPath()),BasicFileAttributes.class);
			this.creationTime = attributes.creationTime().toMillis();
			// get data as base64
			this.data = Utils.encodeFileToBase64(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// another constructor just for simplify something
	public File(String fileName) {
		this(new java.io.File(fileName));
	}
	

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setCreationTime(long time) {
		creationTime = time;	
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public void setData(String base64) {
		data = base64;		
	}


	@Override
	public String getDataAsBase64() {
		return data;
	}
	
}