package it.unibo.droneMission.systems.headquarter.storage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import it.unibo.droneMission.interfaces.headquarter.DataBaseTables;
import it.unibo.droneMission.interfaces.headquarter.IDataBase;
import it.unibo.droneMission.interfaces.headquarter.IMission;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.interfaces.messages.ISensorsData;
import it.unibo.droneMission.messages.Command;
import it.unibo.droneMission.messages.Factory;
import it.unibo.droneMission.messages.File;
import it.unibo.droneMission.messages.Notify;
import it.unibo.droneMission.messages.PicturePackage;
import it.unibo.droneMission.messages.Reply;
import it.unibo.droneMission.messages.SensorsData;

public abstract class DataBase extends Storage implements IDataBase {

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
	
	protected int mission = -1;
	protected boolean isonmission = false;
	
	@Override
	public void init() {
		// TODO NEEDS IMPLEMENTATION

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
	public void startMission() {
		long start = new Date().getTime();
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.MISSIONS_COLUMN_START, "" + start);
		set.put(DataBaseTables.MISSIONS_COLUMN_END, "-1");
		
		this.from(DataBaseTables.MISSIONS_TABLENAME);
		this.mission = this.insert(set);
		this.isonmission = true;
	}
	
	@Override
	public void endMission() {
		int mission = getCurrentMissionID();
		long end = new Date().getTime();
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.MISSIONS_COLUMN_END, "" + end);
		
		this.from(DataBaseTables.MISSIONS_TABLENAME);
		this.where(DataBaseTables.MISSIONS_COLUMN_ID, "" + mission);
		this.update(set);
		
		this.mission = -1;
		this.isonmission = false;
	}
	
	@Override
	public boolean isOnMission() {
		return getCurrentMissionID() > 0;
	}

	
	@Override
	public void resetCurrentMissionID() {
		this.mission = -1;
	}
	
	@Override
	public int getCurrentMissionID() {
		
		if (this.mission > 0)
			return this.mission;
		else {
			this.from(DataBaseTables.MISSIONS_TABLENAME);
			this.orderBy(DataBaseTables.MISSIONS_COLUMN_START, DESC);
			this.limit(1);
			ResultSet set = this.get();
			try {
				if (set.next()) {
					long end = set.getLong(DataBaseTables.MISSIONS_COLUMN_END);
					if (end == -1)
						this.mission = set.getInt(DataBaseTables.COMMANDS_COLUMN_ID);
				}
			} catch (SQLException e) {
				System.err.println("Error catching current mission ID - getCurrentMissionID");
				e.printStackTrace();
			}
			return this.mission;
		}
	}
	
	@Override
	public IMission getMission(int mission_id) {
		
		if (mission_id <= 0) {
			// get last mission ID
			this.from(DataBaseTables.MISSIONS_TABLENAME);
			this.orderBy(DataBaseTables.MISSIONS_COLUMN_START, DESC);
			this.limit(1);
			ResultSet set = this.get();
			try {
				if (set.next()) {
					mission_id = set.getInt(DataBaseTables.COMMANDS_COLUMN_ID);
				}
			} catch (SQLException e) {
				System.err.println("Error catching last mission ID - getMission()");
				e.printStackTrace();
			}
		}
		
		long startTime = -1;
		long endTime = -1;
		
		this.from(DataBaseTables.MISSIONS_TABLENAME);
		this.where(DataBaseTables.MISSIONS_COLUMN_ID, "" + mission_id);
		ResultSet missionSet = this.get();
		
		try {
			if (missionSet.next()) {
				startTime = missionSet.getLong(DataBaseTables.MISSIONS_COLUMN_START);
				endTime = missionSet.getLong(DataBaseTables.MISSIONS_COLUMN_END);
			}
		} catch (SQLException e) {
			System.err.println("Error catchig mission information - getMission(int mission_id)");
			e.printStackTrace();
			return null;
		}
	
		Mission mission = new Mission(mission_id);
		mission.setStartTime(startTime);
		mission.setEndTime(endTime);
		mission.setCommands(getCommandsByMission(mission_id));
		mission.setPicturePackages(getPicturePackagesByMission(mission_id));
		mission.setNotifies(getNotifiesByMission(mission_id));
		mission.setSensorsDatas(getSensorsDatasByMission(mission_id));
		
		return mission;

	}
	
	@Override
	public List<IMission> getPastMissions() {
		ArrayList<IMission> list = new ArrayList<>();
		this.from(DataBaseTables.MISSIONS_TABLENAME);
		ResultSet set = this.get();
		try {
			while(set.next()) {
				long start = set.getLong(DataBaseTables.MISSIONS_COLUMN_START);
				long end = set.getLong(DataBaseTables.MISSIONS_COLUMN_END);
				long id = set.getLong(DataBaseTables.MISSIONS_COLUMN_ID);
				Mission m = new Mission(id);
				m.setStartTime(start);
				m.setEndTime(end);
				list.add(m);
			}
		} catch (SQLException e) {
			System.err.println("Error catching all missions - getPastMissions()");
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void storeCommandAndReply(ICommand command, IReply reply) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.COMMANDS_COLUMN_TYPE, "" + command.getType());
		set.put(DataBaseTables.COMMANDS_COLUMN_TIME, "" + command.getTime());
		set.put(DataBaseTables.COMMANDS_COLUMN_MISSION, "" + getCurrentMissionID());
		set.put(DataBaseTables.COMMANDS_COLUMN_VALUE, "" + command.getValue());
		
		this.from(DataBaseTables.COMMANDS_TABLENAME);
		int command_id = this.insert(set);
		this._storeCommandReply(reply, command_id);
	}

	private void _storeCommandReply(IReply reply, int command_id) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.REPLIES_COLUMN_TYPE, "" + reply.getType());
		set.put(DataBaseTables.REPLIES_COLUMN_TIME, "" + reply.getTime());
		set.put(DataBaseTables.REPLIES_COLUMN_COMMAND, "" + command_id);
		set.put(DataBaseTables.REPLIES_COLUMN_VALUE, "" + reply.getValue());
		
		this.from(DataBaseTables.REPLIES_TABLENAME);
		this.insert(set);
		
	}
	
	@Override
	public LinkedHashMap<ICommand, IReply> getLatestCommands(int n) {
		int mission = getCurrentMissionID();
		return _getCommandsWithLimitAndMissionID(n, mission);
	}

	@Override
	public LinkedHashMap<ICommand, IReply> getCommandsByMission(int missionID) {
		return _getCommandsWithLimitAndMissionID(-1, missionID);
	}

	private LinkedHashMap<ICommand, IReply> _getCommandsWithLimitAndMissionID(int limit, int mission_id) {
		
		if (limit == 0)
			return null;
		
		if (mission_id <= 0)
			mission_id = getCurrentMissionID();
		
		this.from(DataBaseTables.COMMANDS_TABLENAME);
		this.where(DataBaseTables.COMMANDS_COLUMN_MISSION, "" + mission_id);
		this.orderBy(DataBaseTables.COMMANDS_COLUMN_ID, this.DESC);
		
		if (limit > 0)
			this.limit(limit);
		
		ResultSet set = this.get();
		
		LinkedHashMap<ICommand, IReply> list = new LinkedHashMap<ICommand, IReply>();
		
		try {
			while (set.next()) {
				int type = set.getInt(DataBaseTables.COMMANDS_COLUMN_TYPE);
				int value = set.getInt(DataBaseTables.COMMANDS_COLUMN_VALUE);
				long time = set.getLong(DataBaseTables.COMMANDS_COLUMN_TIME);
				Command cmd = new Command(type, value, time);
				int id = set.getInt(DataBaseTables.COMMANDS_COLUMN_ID);
				IReply rpl = _getReplyByCommandID(id);
				list.put(cmd, rpl);
			}
		} catch (SQLException e) {
			System.err.println("Error ciclying result from getLatestCommands(int n)");
			e.printStackTrace();
			return null;
		}
		
		return list;
	}
	
	private IReply _getReplyByCommandID(int command_id) {
		this.from(DataBaseTables.REPLIES_TABLENAME);
		this.where(DataBaseTables.COMMANDS_COLUMN_ID, "" + command_id);
		
		ResultSet set = this.get();
		Reply reply = null;
		try {
			if (set.next()) {
				int type = set.getInt(DataBaseTables.REPLIES_COLUMN_TYPE);
				String value = set.getString(DataBaseTables.REPLIES_COLUMN_VALUE);
				long time = set.getLong(DataBaseTables.REPLIES_COLUMN_TIME);
				reply = new Reply(type, value, time);
			}
		} catch (SQLException e) {
			System.err.println("Error ciclying result from getLatestCommands(int n)");
			e.printStackTrace();
		}
		
		return reply;
	}
	
	@Override
	public void storeNotify(INotify notify) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.NOTIFIES_COLUMN_TYPE, "" + notify.getType());
		set.put(DataBaseTables.NOTIFIES_COLUMN_TIME, "" + notify.getTime());
		set.put(DataBaseTables.NOTIFIES_COLUMN_MISSION, "" + getCurrentMissionID());
		set.put(DataBaseTables.NOTIFIES_COLUMN_VALUE, "" + notify.getValue());
		
		this.from(DataBaseTables.NOTIFIES_TABLENAME);
		this.insert(set);

	}

	@Override
	public List<INotify> getLatestNotifies(int n) {
		int mission = getCurrentMissionID();
		return _getNotifiesWithLimitAndMissionID(n, mission);
	}

	public List<INotify> getNotifiesByMission(int missionID) {
		return _getNotifiesWithLimitAndMissionID(-1, missionID);
	}
	
	private List<INotify> _getNotifiesWithLimitAndMissionID(int limit, int mission_id) {
		if (limit == 0)
			return null;
		
		if (mission_id <= 0)
			mission_id = getCurrentMissionID();
		
		this.from(DataBaseTables.NOTIFIES_TABLENAME);
		this.where(DataBaseTables.NOTIFIES_COLUMN_MISSION, "" + mission_id);
		this.orderBy(DataBaseTables.NOTIFIES_COLUMN_ID, this.DESC);
		if (limit > 0)
			this.limit(limit);
		
		ResultSet set = this.get();
		ArrayList<INotify> list = new ArrayList<INotify>();
		
		try {
			while (set.next()) {
				int type = set.getInt(DataBaseTables.NOTIFIES_COLUMN_TYPE);
				String value = set.getString(DataBaseTables.NOTIFIES_COLUMN_VALUE);
				long time = set.getLong(DataBaseTables.NOTIFIES_COLUMN_TIME);
				Notify notify = new Notify(type, value, time);
				list.add(notify);
			}
				
		} catch (SQLException e) {
			System.err.println("Error checking if there's data in oldest notifies query - getLatestNotifies()");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void storeSensorsData(ISensorsData data) {
		_storeSensorsData(data, false);
	}

	private int _storeSensorsData(ISensorsData data, boolean has_picture) {
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.SENSORS_COLUMN_DATA, "" + data.toJSON());
		set.put(DataBaseTables.SENSORS_COLUMN_MISSION, "" + getCurrentMissionID());
		set.put(DataBaseTables.SENSORS_COLUMN_TIME, "" + data.getTime());
		set.put(DataBaseTables.SENSORS_COLUMN_HASPICTURE, has_picture ? "1" : "0");
		
		this.from(DataBaseTables.SENSORS_TABLENAME);
		
		return this.insert(set);
	}

	@Override	
	public List<ISensorsData> getLatestSensorsDatas(int n) {
		int mission = getCurrentMissionID();
		return _getSensorsDatasWithLimitAndMissionID(n, mission);
	}

	@Override
	public List<ISensorsData> getSensorsDatasByMission(int missionID) {
		return _getSensorsDatasWithLimitAndMissionID(-1, missionID);
	}
	
	private List<ISensorsData> _getSensorsDatasWithLimitAndMissionID(int limit, int mission_id) {
		if (limit == 0)
			return null;
		
		if (mission_id <= 0)
			mission_id = getCurrentMissionID();
		
		this.from(DataBaseTables.SENSORS_TABLENAME);
		this.where(DataBaseTables.SENSORS_COLUMN_MISSION, "" + mission_id);
		this.where(DataBaseTables.SENSORS_COLUMN_HASPICTURE, "0");
		this.orderBy(DataBaseTables.SENSORS_COLUMN_ID, this.DESC);
		
		if (limit > 0)
			this.limit(limit);
		
		ResultSet set = this.get();
		
		if (set == null)
			return null;
		
		ArrayList<ISensorsData> list = new ArrayList<ISensorsData>();
		try {
			while (set.next()) {
				String data = set.getString(DataBaseTables.SENSORS_COLUMN_DATA);
				list.add(Factory.createSensorsData(data));
			}
			
		} catch (SQLException e) {
			System.err.println("Error catching value from getLatestSensorsDatas(int n)");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void storePicturePackage(IPicturePackage pack) {
		
		int sensorsID = _storeSensorsData(pack.getSensorsData(), true);
				
		IFile picture = pack.getFile();
		
		storeFile(picture);
		
		Hashtable<String, String> set = new Hashtable<>();
		set.put(DataBaseTables.PICTURES_COLUMN_FILE_TIME, "" + picture.getCreationTime());
		set.put(DataBaseTables.PICTURES_COLUMN_FILE_NAME, "" + picture.getName());
		set.put(DataBaseTables.PICTURES_COLUMN_SENSORS, "" + sensorsID);
		set.put(DataBaseTables.PICTURES_COLUMN_MISSION, "" + getCurrentMissionID());
		
		
		this.from(DataBaseTables.PICTURES_TABLENAME);
		this.insert(set);

	}
	
	@Override
	public List<IPicturePackage> getPicturePackagesByMission(int missionID) {
		return _getPicturePackagesWithLimitAndMissiondID(-1, missionID);
	}
	
	@Override 
	public List<IPicturePackage> getLatestPicturePackages(int n) {
		int mission = getCurrentMissionID();
		return _getPicturePackagesWithLimitAndMissiondID(n, mission);
	}
	
	private List<IPicturePackage> _getPicturePackagesWithLimitAndMissiondID(int limit, int mission_id) {

		if(limit == 0)
			return null;
		
		if (mission_id <= 0)
			mission_id = getCurrentMissionID();
		
		int sensors_id = -1;
		
		this.from(DataBaseTables.PICTURES_TABLENAME);
		this.where(DataBaseTables.PICTURES_COLUMN_MISSION, "" + mission_id);
		this.orderBy(DataBaseTables.PICTURES_COLUMN_ID, this.DESC);
		
		if (limit > 0)
			this.limit(limit);
		
		ResultSet set = this.get();
		
		ArrayList<IPicturePackage> list = new ArrayList<>();
		
		if (set == null)
			return null;
		
		try {
			while (set.next()) {
				File file = new File(); 
				SensorsData sensors = new SensorsData();
				
				try {
					String filename = set.getString(DataBaseTables.PICTURES_COLUMN_FILE_NAME);
					long time = set.getLong(DataBaseTables.PICTURES_COLUMN_FILE_TIME);
					sensors_id = set.getInt(DataBaseTables.PICTURES_COLUMN_SENSORS);
					
					file.setName(filename);
					file.setCreationTime(time);
				
					
				} catch (SQLException e) {
					System.err.println("Error catching file information - getLatestPicturePackages()");
					e.printStackTrace();
					continue;
				}
				
				// get sensors data referees to file
				this.from(DataBaseTables.SENSORS_TABLENAME);
				this.where(DataBaseTables.SENSORS_COLUMN_ID, "" + sensors_id);
				ResultSet setSensors =  this.get();

				if (setSensors == null)
					continue;
				
				try {
					setSensors.next();
					String data = setSensors.getString(DataBaseTables.SENSORS_COLUMN_DATA);
					sensors = Factory.createSensorsData(data);
				} catch (SQLException e) {
					System.err.println("Error catching sensors information - getLatestPicturePackages()");
					e.printStackTrace();
					continue;
				}
				
				list.add(new PicturePackage(sensors, file));
			}
		} catch (SQLException e) {
			System.err.println("Error cycling set in getLatestPicturePackages()");
			e.printStackTrace();
		}
		return list;
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
	public List<IFile> getLatestFiles(int n) {
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
