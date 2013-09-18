package it.unibo.droneMission.systems.headquarter;

import it.unibo.droneMission.interfaces.headquarter.IStorage;

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

}
