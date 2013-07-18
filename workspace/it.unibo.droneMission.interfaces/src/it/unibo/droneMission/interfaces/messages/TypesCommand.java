package it.unibo.droneMission.interfaces.messages;

public class TypesCommand {
	
	// replies
	public final static int REPLY_OK = 0;
	public final static int REPLY_NO = -1;
	public final static int REPLY_FAIL = -2;
	
	// start and stop
	public final static int START_MISSION = 1;
	public final static int END_MISSION = 2;
	
	// speed commands
	public final static int SPEED_SET = 3;
	public final static int SPEED_INCREASE = 4;
	public final static int SPEED_DECREASE = 5;
		
}
