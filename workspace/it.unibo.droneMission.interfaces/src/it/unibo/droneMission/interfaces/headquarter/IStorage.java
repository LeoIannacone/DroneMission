package it.unibo.droneMission.interfaces.headquarter;

import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

public interface IStorage {

	// init storage
	public void init();
	
	// commands
	public void storeCommand(ICommand command);
	public void storeCommandReply(IReply reply);
	public ICommand getCommandToSend();
	
	// notify
	public void storeNotify(INotify notify);
	public INotify getLatestNotify();
	
	// sensors data
	public void storeSensorsData(ISensorsData data);
	public ISensorsData getLatestSensorsData();
	public ISensorsData getSensorsData(long time);
	
	// picture package
	public void storePicturePackage(IPicturePackage pack);
	public IPicturePackage getLatestPicturePackage();
	
	// general file
	public void storeFile(IFile file);
	public IFile getFile(String filename);
	public IFile getFile(long time);
	
	// for debuggin purpose
	public void setDebug(int level);
	public void debug(String s, int level);

}
