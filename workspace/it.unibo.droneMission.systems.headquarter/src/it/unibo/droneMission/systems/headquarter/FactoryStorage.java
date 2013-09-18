package it.unibo.droneMission.systems.headquarter;

import it.unibo.droneMission.interfaces.headquarter.IStorage;

public class FactoryStorage {

	public static IStorage getInstance(String storagetype) throws Exception {
		if (storagetype.equalsIgnoreCase("mysql"))
			try {
				return MySQL.getInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			throw new Exception("Type storage: " + storagetype + "is not valid.");
			return null;
	}

}
