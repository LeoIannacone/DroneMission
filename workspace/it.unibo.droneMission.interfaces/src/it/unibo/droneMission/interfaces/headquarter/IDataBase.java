package it.unibo.droneMission.interfaces.headquarter;

import java.sql.ResultSet;
import java.util.Hashtable;
/**
 * @model 
 */
public interface IDataBase extends IStorage {

	/**
	 * @model 
	 */
	public void connect();
	/**
	 * @model 
	 */
	public void disconnect();
	/**
	 * @model 
	 */
	public boolean isConnected();
	
	/**
	 * @model 
	 */
	public void setUsername(String username);
	/**
	 * @model 
	 */
	public String getUsername();
	
	/**
	 * @model 
	 */
	public void setPassword(String password);
	/**
	 * @model 
	 */
	public String getPassword();
	
	/**
	 * @model 
	 */
	public void setHostname(String hostname);
	/**
	 * @model 
	 */
	public String getHostname();
	
	/**
	 * @model 
	 */
	public void setPort(int port);
	/**
	 * @model 
	 */
	public int getPort();
	
	/**
	 * @model 
	 */
	public void setDatabaseName(String dbname);
	/**
	 * @model 
	 */
	public String getDatabaseName();
	
	
	// common db interaction
	/**
	 * @model 
	 */
	public void select(String column);
	/**
	 * @model 
	 */
	public void select(String[] columns);
	
	/**
	 * @model 
	 */
	public void from(String table);
	/**
	 * @model 
	 */
	public void from(String[] tables);
	
	/**
	 * @model 
	 */
	public void where(String key, String value);
	/**
	 * @model 
	 */
	public void where(Hashtable<String, String> set);
	
	/**
	 * @model 
	 */
	public void orderBy(String column, String direction);
	/**
	 * @model 
	 */
	public void limit(int n);
	/**
	 * @model 
	 */
	public void offset(int n);
	
	/**
	 * @model 
	 */
	public int update(Hashtable<String, String> set);
	/**
	 * @model 
	 */
	public int insert(Hashtable<String, String> set);
	/**
	 * @model 
	 */
	public ResultSet get();
	
}
