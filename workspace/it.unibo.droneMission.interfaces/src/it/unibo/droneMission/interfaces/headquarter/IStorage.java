package it.unibo.droneMission.interfaces.headquarter;

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
	
	// commands
	public void storeCommand(ICommand command);
	public void storeCommandReply(IReply reply);
	public ICommand getCommandToSend();
	public List<ICommand> getLatestCommands(int n);
	
	// notify
	public void storeNotify(INotify notify);
	public INotify getLatestNotify();
	public List<INotify> getLatestNotifies(int n);
	
	// sensors data
	public void storeSensorsData(ISensorsData data);
	public ISensorsData getSensorsData(long time);
	public ISensorsData getLatestSensorsData();
	public List<ISensorsData> getLatestSensorsDatas(int n);
	
	// picture package
	public void storePicturePackage(IPicturePackage pack);
	public IPicturePackage getLatestPicturePackage();
	public List<IPicturePackage> getLatestPicturePackages(int n);
	
	// general file
	public void storeFile(IFile file);
	public IFile getFile(String filename);
	public IFile getFile(long time);
	public List<IFile> getLatestFiles(int n);
	
	// for debuggin purpose
	public void setDebug(int level);
	public void debug(String s, int level);

}
