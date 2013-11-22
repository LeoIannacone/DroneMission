package it.unibo.droneMission.systems.headquarter;

import java.util.List;

import it.unibo.droneMission.interfaces.headquarter.IStorage;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

public abstract class Storage implements IStorage {

	protected int DEBUG = -1;

	@Override
	public void setDebug(int level) {
		DEBUG = level;
	}
	
	@Override
	public void debug(String s, int level) {
		if(level <= DEBUG) {
			System.err.println(String.format("debug (%d): %s", level, s));
		}
	}
	
	@Override
	public INotify getLatestNotify() {
		List<INotify> list = getLatestNotifies(1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Override
	public ISensorsData getLatestSensorsData() {
		List<ISensorsData> list = getLatestSensorsDatas(1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public IPicturePackage getLatestPicturePackage() {
		List<IPicturePackage> list = getLatestPicturePackages(1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

}
