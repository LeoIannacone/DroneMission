package it.unibo.droneMission.systems.headquarter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import it.unibo.droneMission.interfaces.headquarter.CommandsStatus;
import it.unibo.droneMission.interfaces.headquarter.DataBaseTables;
import it.unibo.droneMission.interfaces.headquarter.IDataBase;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;
import it.unibo.droneMission.prototypes.messages.Command;

public abstract class DataBase implements IDataBase {

	protected int DEBUG = -1;
	
	public final String ASC = "ASC";
	public final String DESC = "DESC";
	
	protected String dbname;
	protected String username;
	protected String password;
	protected String host;
	protected int port;
	
	protected ArrayList<String> select;
	protected ArrayList<String> from;
	protected Hashtable<String, String> where;
	protected String orderBy;
	protected String orderByDirection;
	protected int limit;
	protected int offset;
	
	protected Connection db;
	protected Statement stmt;
	
	@Override
	public void init() {
		// TODO NEEDS IMPLEMENTATION

	}
	
	public void setDebug(int level) {
		this.DEBUG = level;
	}
	
	protected void debug(String s, int level) {
		if(level <= DEBUG) {
			System.err.println(String.format("debug (%d): %s", level, s));
		}
	}
	
	public boolean checkTables() {
		// TODO NEEDS IMPLEMENTATION

		try {
			DatabaseMetaData metadata = db.getMetaData();
		} catch (SQLException e) {
			System.err.println("Error trying to get metadata.");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void disconnect() {
		try {
			stmt.close();
			db.close();
		} catch (SQLException e) {
			System.err.println("Error trying to closing database connection.");
			e.printStackTrace();
		}
	}

	@Override
	public boolean isConnected() {
		try {
			return db != null && !db.isClosed();
		} catch (SQLException e) {
			System.err.println("Error trying to check if connection is closed.");
			e.printStackTrace();
		}
		return false;
	}
		@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setHostname(String hostname) {
		this.host = hostname;
	}

	@Override
	public String getHostname() {
		return host;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int getPort() {
		return this.port;
	}

	@Override
	public void setDatabaseName(String dbname) {
		this.dbname = dbname;		
	}

	@Override
	public String getDatabaseName() {
		return dbname;
	}
	

	@Override
	public void storeCommand(ICommand command) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.COMMANDS_COLUMN_TYPE, "" + command.getType());
		set.put(DataBaseTables.COMMANDS_COLUMN_TIME, "" + (new Date()).getTime());
		set.put(DataBaseTables.COMMANDS_COLUMN_STATUS, "" + CommandsStatus.TO_SEND);
		set.put(DataBaseTables.COMMANDS_COLUMN_VALUE, "" + command.getValue());
		
		this.from(DataBaseTables.COMMANDS_TABLENAME);
		this.insert(set);
	}

	@Override
	public void storeCommandReply(IReply reply) {
		ResultSet cmd = _getOldestCommandToSend_SQL();
		if (cmd == null)
			return;
		
		Hashtable<String, String> set = new Hashtable<>();
		
		try {
			cmd.next();
			String cmd_id = cmd.getString(DataBaseTables.COMMANDS_COLUMN_ID);
			// update cmd status
			set.put(DataBaseTables.COMMANDS_COLUMN_STATUS, "" + CommandsStatus.REPLIED);
			this.from(DataBaseTables.COMMANDS_TABLENAME);
			this.where(DataBaseTables.COMMANDS_COLUMN_ID, cmd_id);
			this.update(set);
			
			// store reply
			set.clear();
			set.put(DataBaseTables.REPLIES_COLUMN_TYPE, "" + reply.getType());
			set.put(DataBaseTables.REPLIES_COLUMN_TIME, "" + (new Date()).getTime());
			set.put(DataBaseTables.REPLIES_COLUMN_COMMAND, cmd_id);
			set.put(DataBaseTables.REPLIES_COLUMN_VALUE, "" + reply.getValue());
			
			this.from(DataBaseTables.REPLIES_TABLENAME);
			this.insert(set);
			
		} catch (SQLException e) {
			System.err.println("Error catching command id in storeCommandReply().");
			e.printStackTrace();
		}
		
	}
	
	private ResultSet _getOldestCommandToSend_SQL() {
		
		this.from(DataBaseTables.COMMANDS_TABLENAME);
		this.where(DataBaseTables.COMMANDS_COLUMN_STATUS, "" + CommandsStatus.TO_SEND);
		this.orderBy(DataBaseTables.COMMANDS_COLUMN_ID, this.DESC);
		this.limit(1);
		
		ResultSet cmd = this.get();
		try {
			if (cmd.isBeforeFirst())
				return cmd;
		} catch (SQLException e) {
			System.err.println("Error checking if there's data in oldest command query - getOldestCommandToSend()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ICommand getCommandToSend() {
		ResultSet cmdSQL = _getOldestCommandToSend_SQL();
		if (cmdSQL == null)
			return null;
		int type;
		int value;
		try {
			cmdSQL.next();
			type = cmdSQL.getInt(DataBaseTables.COMMANDS_COLUMN_ID);
		} catch (SQLException e) {
			System.err.println("SQL Error getting type from Command - getCommandToSend()");
			e.printStackTrace();
			return null;
		}
		try {
			value = cmdSQL.getInt(DataBaseTables.COMMANDS_COLUMN_VALUE);
		} catch (SQLException e) {
			System.err.println("SQL Error getting value from Command - getCommandToSend()");
			e.printStackTrace();
			return null;
		}
		Command cmd = new Command(type, value);
		return cmd;
	}

	@Override
	public void storeNotify(INotify notify) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.NOTIFIES_COLUMN_TYPE, "" + notify.getType());
		set.put(DataBaseTables.NOTIFIES_COLUMN_TIME, "" + (new Date()).getTime());
		set.put(DataBaseTables.NOTIFIES_COLUMN_VALUE, "" + notify.getValue());
		
		this.from(DataBaseTables.NOTIFIES_TABLENAME);
		this.insert(set);

	}

	@Override
	public INotify getLatestNotify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeSensorsData(ISensorsData data) {
		_storeSensorsDataAndGetResult_SQL(data, false);
	}

	private ResultSet _storeSensorsDataAndGetResult_SQL(ISensorsData data, boolean getResult) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.SENSORS_COLUMN_DATA, "" + data.toJSON());
		set.put(DataBaseTables.SENSORS_COLUMN_TIME, "" + data.getTime());
		
		this.from(DataBaseTables.SENSORS_TABLENAME);
		
		if(this.insert(set) > 0 && getResult)
			return _getLastSensorsData_SQL();
		
		return null;
	}
	
	private ResultSet _getLastSensorsData_SQL() {
		this.from(DataBaseTables.SENSORS_TABLENAME);
		this.limit(1);
		this.orderBy(DataBaseTables.SENSORS_COLUMN_ID, this.DESC);
		return this.get();
	}
	
	@Override
	public ISensorsData getLatestSensorsData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISensorsData getSensorsData(long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storePicturePackage(IPicturePackage pack) {
		
		ResultSet sensorsSQL = _storeSensorsDataAndGetResult_SQL(pack.getSensorsData(), true);
		int sensorsID;
		try {
			sensorsSQL.next();
			sensorsID = sensorsSQL.getInt(DataBaseTables.SENSORS_COLUMN_ID);
		} catch (SQLException e) {
			System.err.println("Error getting ID from sensors ResultSet - storePicturePackage(IPicturePackage pack)");
			e.printStackTrace();
			return;
		}
		
		IFile picture = pack.getFile();
		
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.PICTURES_COLUMN_FILE_TIME, "" + picture.getCreationTime());
		set.put(DataBaseTables.PICTURES_COLUMN_FILE_NAME, "" + picture.getName());
		set.put(DataBaseTables.PICTURES_COLUMN_FILE_DATA, "" + picture.getDataAsBase64());
		set.put(DataBaseTables.PICTURES_COLUMN_SENSORS, "" + sensorsID);
		
		this.from(DataBaseTables.PICTURES_TABLENAME);
		this.insert(set);

	}
	
	@Override 
	public IPicturePackage getLatestPicturePackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeFile(IFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public IFile getFile(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFile getFile(long time) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void select(String column) {
		this.select.add(column);
	}

	@Override
	public void select(String[] columns) {
		for (String c : columns)
			this.select(c);
	}

	@Override
	public void from(String table) {
		this.from.add(table);
	}

	@Override
	public void from(String[] tables) {
		for (String t : tables)
			this.from(t);
	}

	@Override
	public void where(String key, String value) {
		this.where.put(key, value);
	}

	@Override
	public void where(Hashtable<String, String> set) {
		this.where.putAll(set);
		
	}

	@Override
	public void orderBy(String column, String direction) {
		this.orderBy = column;
		this.orderByDirection = direction;
	}

	@Override
	public void limit(int n) {
		this.limit = n;
	}

	@Override
	public void offset(int n) {
		this.offset = n;
	}
	
	protected void resetSQLParams() {
		select = new ArrayList<String>();
		from = new ArrayList<String>();
		where = new Hashtable<String, String>();
		orderBy = "";
		orderByDirection = "";
		limit = -1;
		offset = -1;
	}
	
}
