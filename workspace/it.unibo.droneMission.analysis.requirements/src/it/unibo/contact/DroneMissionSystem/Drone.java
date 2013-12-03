package it.unibo.contact.DroneMissionSystem;

public class Drone extends DroneSupport {
	
	private int num_sensors_sent;
	private int MAX_SENSORS_SENT = 20;
	
	public Drone(String s) throws Exception{
		super(s);
		num_sensors_sent = 0;
	}
	
	
	/*
	 * Considering cmdString is in format
	 * cmdName value
	 */
	
	private static String cleanCommand(String cmdString) {
		return cmdString.replace("\"","").replace("'","");
	}
	
	public static String getCommandName(String cmdString) {
		return cleanCommand(cmdString).split(" ")[0] ;
	}
	
	public static String getCommandValue(String cmdString) {
		cmdString = cleanCommand(cmdString);
		String[] info = cmdString.split(" ");
		if (info.length >= 1)
			return info[1];
		return "";
	}
	

	@Override
	protected void startMission() throws Exception {
		env.println("START MISSION");
	}


	@Override
	protected void endMission() throws Exception {
		env.println("STOP MISSION");
	}


	@Override
	protected String getDataFromSensors() throws Exception {
		String result = "";
		if (num_sensors_sent < MAX_SENSORS_SENT) {
			int odomenter = (int)(Math.random() * 100);
			int speedometer = (int)(Math.random() * 100);
			int fuel = (int)(Math.random() * 100);
			result = String.format("odomoter:%s;speedometer:%s;fuel:%s", odomenter, speedometer, fuel);
		}
		else
			result = Messages.SENSORS_LAST;
		num_sensors_sent++;
		env.println("Sending sensor: " + result);
		return result;
	}


	@Override
	protected String getDataPhoto() throws Exception {
		String photo = "photoX;dataY;timeZ";
		env.println("Sending photo: " + photo);
		return photo;
	}

	@Override
	protected String handleCommand(String cmd) throws Exception {
		cmd = cleanCommand(cmd);
		if(cmd.startsWith(Messages.COMMAND_SETSPEED) || cmd.startsWith(Messages.COMMAND_START)) {
			env.println("COMMAND handler OK: " + cmd);
			return Messages.REPLY_OK;
		}
			
		else {
			env.println("COMMAND handler FAIL: " + cmd);
			return Messages.REPLY_NO;
		}
			
	}


	@Override
	protected boolean isCommandStart(String cmd) throws Exception {
		env.println(cmd + " " + Messages.COMMAND_START);
		return cleanCommand(cmd).equalsIgnoreCase(Messages.COMMAND_START);
	}


	@Override
	protected String getFailReplyToCommand() throws Exception {
		return Messages.REPLY_NO;
	}


	@Override
	protected String getOkReplyToCommand() throws Exception {
		return Messages.REPLY_OK;
	}


	@Override
	protected String getNotifyStart() throws Exception {
		return Messages.NOTIFY_START;
	}


	@Override
	protected String getNotifyEnd() throws Exception {
		return Messages.NOTIFY_END;
	}


	@Override
	protected boolean isMissionEnding() throws Exception {
		return num_sensors_sent >= MAX_SENSORS_SENT;
	}
}
