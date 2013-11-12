package it.unibo.droneMission.interfaces.headquarter;

import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

import java.util.LinkedHashMap;
import java.util.List;

public interface IMission {

	public void setId(long id);
	public long getId();
	
	public abstract void setCommands(LinkedHashMap<ICommand, IReply> commands);

	public abstract LinkedHashMap<ICommand, IReply> getCommands();

	public abstract List<INotify> getNotifies();

	public abstract void setNotifies(List<INotify> notifies);

	public abstract List<IPicturePackage> getPicturePackages();

	public abstract void setPicturePackages(
			List<IPicturePackage> picturePackages);

	public abstract long getStartTime();

	public abstract void setStartTime(long startTime);

	public abstract long getEndTime();

	public abstract void setEndTime(long endTime);

	List<ISensorsData> getSensorsDatas();

	void setSensorsDatas(List<ISensorsData> sensorsDatas);

}