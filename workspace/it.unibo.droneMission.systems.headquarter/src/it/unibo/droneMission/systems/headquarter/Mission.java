package it.unibo.droneMission.systems.headquarter;

import it.unibo.droneMission.interfaces.headquarter.IMission;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Mission implements IMission {

	protected LinkedHashMap<ICommand, IReply> commands;
	protected List<INotify> notifies;
	protected List<ISensorsData> sensorsDatas;
	protected List<IPicturePackage> picturePackages;

	protected long startTime;
	protected long endTime;
	
	public Mission() {
		commands = new LinkedHashMap<ICommand, IReply>();
		notifies = new ArrayList<INotify>();
		sensorsDatas = new ArrayList<ISensorsData>();
		picturePackages = new ArrayList<IPicturePackage>();
		startTime = -1;
		endTime = -1;
	}
	
	@Override
	public void setCommands(LinkedHashMap<ICommand, IReply> commands) {
		this.commands = commands;
	}

	@Override
	public LinkedHashMap<ICommand, IReply> getCommands() {
		return commands;
	}

	@Override
	public List<INotify> getNotifies() {
		return notifies;
	}

	@Override
	public void setNotifies(List<INotify> notifies) {
		this.notifies = notifies;
	}

	@Override
	public List<IPicturePackage> getPicturePackages() {
		return picturePackages;
	}

	@Override
	public void setPicturePackages(List<IPicturePackage> picturePackages) {
		this.picturePackages = picturePackages;
	}

	@Override
	public long getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override
	public List<ISensorsData> getSensorsDatas() {
		return sensorsDatas;
	}

	@Override
	public void setSensorsDatas(List<ISensorsData> sensorsDatas) {
		this.sensorsDatas = sensorsDatas;
	}
	
}
