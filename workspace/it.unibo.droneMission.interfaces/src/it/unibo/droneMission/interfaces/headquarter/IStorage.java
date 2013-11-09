package it.unibo.droneMission.interfaces.headquarter;

import java.util.Hashtable;
import java.util.List;

import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

public interface IStorage {

	// init storage
	public void init();
	
	// mission
	public void startMission();
	public void endMission();
	public IMission getMission(int id);
	
	// commands
	public void storeCommandAndReply(ICommand command, IReply reply);
	public Hashtable<ICommand, IReply> getLatestCommands(int n);
	public Hashtable<ICommand, IReply> getCommandsByMission(int missionID);
	
	// notify
	public void storeNotify(INotify notify);
	public INotify getLatestNotify();
	public List<INotify> getLatestNotifies(int n);
	public List<INotify> getNotifiesByMission(int missionID);
	
	// sensors data
	public void storeSensorsData(ISensorsData data);
	public ISensorsData getLatestSensorsData();
	public List<ISensorsData> getLatestSensorsDatas(int n);
	public List<ISensorsData> getSensorsDatasByMission(int missionID);
	
	// picture package
	public void storePicturePackage(IPicturePackage pack);
	public IPicturePackage getLatestPicturePackage();
	public List<IPicturePackage> getLatestPicturePackages(int n);
	public List<IPicturePackage> getPicturePackagesByMission(int missionID);
	
	// general file
	public void storeFile(IFile file);
	public IFile getFile(String filename);
	public IFile getFile(long time);
	public List<IFile> getLatestFiles(int n);
	
	// for debuggin purpose
	public void setDebug(int level);
	public void debug(String s, int level);

}
