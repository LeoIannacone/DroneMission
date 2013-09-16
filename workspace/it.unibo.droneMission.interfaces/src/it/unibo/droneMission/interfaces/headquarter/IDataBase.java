package it.unibo.droneMission.interfaces.headquarter;

import java.sql.ResultSet;
import java.util.Hashtable;

public interface IDataBase extends IStorage {

	public void connect();
	public void disconnect();
	public boolean isConnected();
	
	public void setUsername(String username);
	public String getUsername();
	
	public void setPassword(String password);
	public String getPassword();
	
	public void setHostname(String hostname);
	public String getHostname();
	
	public void setPort(int port);
	public int getPort();
	
	public void setDatabaseName(String dbname);
	public String getDatabaseName();
	
	
	// common db interaction
	public void select(String column);
	public void select(String[] columns);
	
	public void from(String table);
	public void from(String[] tables);
	
	public void where(String key, String value);
	public void where(Hashtable<String, String> set);
	
	public void orderBy(String column, String direction);
	public void limit(int n);
	public void offset(int n);
	
	public ResultSet update(Hashtable<String, String> set);
	public ResultSet insert(Hashtable<String, String> set);
	public ResultSet get();
		
}
