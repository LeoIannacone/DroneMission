package it.unibo.droneMission.interfaces.headquarter;

public class DataBaseTables {
	
	// Missions table
	public static final String MISSIONS_TABLENAME = "missions";
	public static final String MISSIONS_ID = "id";
	public static final String MISSIONS_START = "starts";
	public static final String MISSIONS_END = "end";
	
	// Commands table
	public static final String COMMANDS_TABLENAME = "commands";
	public static final String COMMANDS_COLUMN_ID = "id";
	public static final String COMMANDS_COLUMN_MISSION = "mission";
	public static final String COMMANDS_COLUMN_TYPE = "type";
	public static final String COMMANDS_COLUMN_TIME = "time";
	public static final String COMMANDS_COLUMN_VALUE = "value";
	
	// Replies table
	public static final String REPLIES_TABLENAME = "replies";
	public static final String REPLIES_COLUMN_ID = "id";
	public static final String REPLIES_COLUMN_TYPE = "type";	
	public static final String REPLIES_COLUMN_VALUE = "value";
	public static final String REPLIES_COLUMN_COMMAND = "command_id";
	public static final String REPLIES_COLUMN_TIME = "time";
	
	// Notifies table
	public static final String NOTIFIES_TABLENAME = "notifies";
	public static final String NOTIFIES_COLUMN_ID = "id";
	public static final String NOTIFIES_COLUMN_MISSION = "mission";
	public static final String NOTIFIES_COLUMN_TYPE = "type";
	public static final String NOTIFIES_COLUMN_VALUE = "value";
	public static final String NOTIFIES_COLUMN_TIME = "time";
	
	// Sensors table
	public static final String SENSORS_TABLENAME = "sensors";
	public static final String SENSORS_COLUMN_ID = "id";
	public static final String SENSORS_COLUMN_MISSION = "mission";
	public static final String SENSORS_COLUMN_TIME = "time";
	public static final String SENSORS_COLUMN_DATA = "data";
	
	// Pictures table
	public static final String PICTURES_TABLENAME = "pictures";
	public static final String PICTURES_COLUMN_ID = "id";
	public static final String PICTURES_COLUMN_MISSION = "mission";
	public static final String PICTURES_COLUMN_SENSORS = "sensors_id";
	public static final String PICTURES_COLUMN_FILE_NAME = "name";
	public static final String PICTURES_COLUMN_FILE_DATA = "data_base64";
	public static final String PICTURES_COLUMN_FILE_TIME = "time_creation";
	
}
