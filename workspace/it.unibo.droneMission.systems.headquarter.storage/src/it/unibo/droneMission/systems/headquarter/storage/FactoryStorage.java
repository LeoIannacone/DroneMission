package it.unibo.droneMission.systems.headquarter.storage;

import it.unibo.droneMission.interfaces.headquarter.IStorage;

public class FactoryStorage {
	
	private static String getStorageName(int type) {
		if (type == MYSQL)
			return "MySql";
		return "NO DATABASE";
	}
	
	public static int MYSQL = 1;

	public static IStorage getInstance(int databaseType) throws Exception {
		if (databaseType == MYSQL)
			try {
				MySQL db = MySQL.getInstance();
				//db.setDebug(3);
				
				if (!db.isConnected()) {
					db.setDatabaseName("dronemission");
					db.setUsername("dronemission");
					db.setPassword("estate");
					db.setHostname("127.0.0.1");
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
			throw new Exception("Type storage: " + getStorageName(databaseType) + "is not valid.");
			return null;
	}

}
