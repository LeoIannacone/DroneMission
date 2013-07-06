package it.unibo.contact.DroneMissionSystem;

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
		return;
	}


	@Override
	protected void startMission() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
