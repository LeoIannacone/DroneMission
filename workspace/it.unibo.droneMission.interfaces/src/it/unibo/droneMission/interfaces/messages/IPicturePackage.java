package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public interface IPicturePackage extends IMessage {
	/**
	 * @model 
	 */
	public void setFile(IFile file);
	
	/**
	 * @model 
	 */
	public IFile getFile();
	
	/**
	 * @model 
	 */
	public void setSensorsData(ISensorsData sensors);
	
	/**
	 * @model 
	 */
	public ISensorsData getSensorsData();
}
