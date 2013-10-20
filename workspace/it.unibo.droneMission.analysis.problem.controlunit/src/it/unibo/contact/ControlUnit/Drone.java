package it.unibo.contact.ControlUnit;

public class Drone extends DroneSupport {
	public Drone(String s) throws Exception{
		super(s);
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
	
	
	public void setSpeed() {
		env.println("SET SPEED: " + curInputMsgContent);
	}

	@Override
	protected void setSpeed(String value) throws Exception {
		env.println("SET SPEED: " + value);
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
		int odomenter = (int)(Math.random() * 100);
		int speedometer = (int)(Math.random() * 100);
		int fuel = (int)(Math.random() * 100);
		return String.format("odomoter:%s;speedometer:%s;fuel:%s", odomenter, speedometer, fuel);
	}


	@Override
	protected String getDataPhoto() throws Exception {
		return "photoX;dataY;timeZ";
	}


	//@Override
	protected boolean executeCommand(String cmd, String v) throws Exception {
		if(cmd.equals("setspeed") && (Integer.parseInt(v)>=60) && (Integer.parseInt(v)<=120)){
			env.println("Set speed to: " + v);
			return true;
		}
		if(cmd.equals("start")){
			env.println("Mission started");
			return true;
		}
		if(cmd.equals("stop")){
			env.println("Mission stopped");
			this.stop=true;
			return true;
		}
		return false;
	}
}
