package it.unibo.droneMission.systems.headquarter;

import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Mission {

	protected Hashtable<ICommand, IReply> commands;
	protected List<INotify> notifies;
	protected List<ISensorsData> sensorsDatas;
	protected List<IPicturePackage> picturePackages;

	protected long startTime;
	protected long endTime;
	
	public Mission() {
		commands = new Hashtable<ICommand, IReply>();
		notifies = new ArrayList<INotify>();
		sensorsDatas = new ArrayList<ISensorsData>();
		picturePackages = new ArrayList<IPicturePackage>();
		startTime = -1;
		endTime = -1;
	}
	
	public void setCommands(Hashtable<ICommand, IReply> commands) {
		this.commands = commands;
	}

	public Hashtable<ICommand, IReply> getCommands() {
		return commands;
	}

	public List<INotify> getNotifies() {
		return notifies;
	}

	public void setNotifies(List<INotify> notifies) {
		this.notifies = notifies;
	}

	public List<IPicturePackage> getPicturePackages() {
		return picturePackages;
	}

	public void setPicturePackages(List<IPicturePackage> picturePackages) {
		this.picturePackages = picturePackages;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
}
