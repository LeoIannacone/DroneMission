package it.unibo.droneMission.systems.headquarter;

import it.unibo.droneMission.interfaces.headquarter.IStorage;

public class FactoryStorage {

	public static IStorage getInstance(String storagetype) throws Exception {
		if (storagetype.equalsIgnoreCase("mysql"))
			try {
				MySQL db = MySQL.getInstance();
				//db.setDebug(3);
				
				if (!db.isConnected()) {
					db.setDatabaseName("dronemission");
					db.setUsername("dronemission");
					db.setPassword("estate");
					db.setHostname("10.1.1.10");
					db.connect();
				}
				
				return db;
				
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
