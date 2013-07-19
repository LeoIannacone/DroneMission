package it.unibo.droneMission.interfaces.messages;

/**
 * @model 
 */
public class TypesCommand {
	
	// replies
	/**
	 * @model 
	 */
	public final static int REPLY_OK = 0;
	/**
	 * @model 
	 */
	public final static int REPLY_NO = -1;
	/**
	 * @model 
	 */
	public final static int REPLY_FAIL = -2;
	
	// start and stop
	/**
	 * @model 
	 */
	public final static int START_MISSION = 1;
	/**
	 * @model 
	 */
	public final static int END_MISSION = 2;
	
	
	// speed commands
	/**
	 * @model 
	 */
	public final static int SPEED_SET = 3;
	/**
	 * @model 
	 */
	public final static int SPEED_INCREASE = 4;
	/**
	 * @model 
	 */
	public final static int SPEED_DECREASE = 5;
	
	// set picture delay
	/**
	 * @model 
	 */
	public final static int PICTURES_DELAY = 6;
}
