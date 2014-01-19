package it.unibo.droneMission.interfaces.headquarter;

import java.util.LinkedHashMap;
import java.util.List;

import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

/**
 * @model 
 */

public interface IStorage {

	// init storage
	/**
	 * @model 
	 */
	public void init();
	
	// mission
	/**
	 * @model 
	 */
	public void startMission();
	/**
	 * @model 
	 */
	public void endMission();
	/**
	 * @model 
	 */
	public boolean isOnMission();
	/**
	 * @model 
	 */
	public int getCurrentMissionID();
	/**
	 * @model 
	 */
	public void resetCurrentMissionID();
	/**
	 * @model 
	 */
	public IMission getMission(int id);
	/**
	 * @model 
	 */
	public List<IMission> getPastMissions();
	
	// commands
	/**
	 * @model 
	 */
	public void storeCommandAndReply(ICommand command, IReply reply);
	/**
	 * @model 
	 */
	public LinkedHashMap<ICommand, IReply> getLatestCommands(int n);
	/**
	 * @model 
	 */
	public LinkedHashMap<ICommand, IReply> getCommandsByMission(int missionID);
	
	// notify
	/**
	 * @model 
	 */
	public void storeNotify(INotify notify);
	/**
	 * @model 
	 */
	public INotify getLatestNotify();
	/**
	 * @model 
	 */
	public List<INotify> getLatestNotifies(int n);
	/**
	 * @model 
	 */
	public List<INotify> getNotifiesByMission(int missionID);
	
	// sensors data
	/**
	 * @model 
	 */
	public void storeSensorsData(ISensorsData data);
	/**
	 * @model 
	 */
	public ISensorsData getLatestSensorsData();
	/**
	 * @model 
	 */
	public List<ISensorsData> getLatestSensorsDatas(int n);
	/**
	 * @model 
	 */
	public List<ISensorsData> getSensorsDatasByMission(int missionID);
	
	// picture package
	/**
	 * @model 
	 */
	public void storePicturePackage(IPicturePackage pack);
	/**
	 * @model 
	 */
	public IPicturePackage getLatestPicturePackage();
	/**
	 * @model 
	 */
	public List<IPicturePackage> getLatestPicturePackages(int n);
	/**
	 * @model 
	 */
	public List<IPicturePackage> getPicturePackagesByMission(int missionID);
	
	// general file
	/**
	 * @model 
	 */
	public String storeFile(IFile file);
	/**
	 * @model 
	 */
	public IFile getFile(String filename);
	/**
	 * @model 
	 */
	public IFile getFile(long time);
	/**
	 * @model 
	 */
	public List<IFile> getLatestFiles(int n);
	
	// for debugging purpose
	/**
	 * @model 
	 */
	public void setDebug(int level);
	/**
	 * @model 
	 */
	public void debug(String s, int level);

}
