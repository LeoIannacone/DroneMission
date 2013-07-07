/*
*  Generated by AN Unibo
*/
package it.unibo.contact.DroneMissionSystem;
//Import generated by the contact spec
//Other Import
import it.unibo.contact.platformuv.*;
import it.unibo.is.interfaces.*;
import it.unibo.is.interfaces.platforms.*;
//import org.eclipse.xtext.xbase.lib.*;
//import org.eclipse.xtext.xbase.lib.Functions.*;
import java.util.Vector;
import it.unibo.contact.platformuv.LindaLike;
import it.unibo.is.interfaces.protocols.IConnInteraction;
//import java.awt.Color;
//For Xbase code 
import org.eclipse.xtext.xbase.lib.Functions.*;
import org.eclipse.xtext.xbase.lib.*;
import it.unibo.baseEnv.basicFrame.EnvFrame;

public abstract class DroneSupport extends Subject{
	private static Drone obj = null;
	private IMessage resCheckMsg;
	private boolean resCheck;
	/*
	* Factory method: returns a singleton
	*/
	public static Drone create(String name) throws Exception{
		if( obj == null ) obj = new Drone(name);
		return obj;
	}
	/* -------------------------------------
	* Local state of the subject
	* --------------------------------------
	*/
	protected int lastMsgNum = 0;
	
	
	//Constructor
	public DroneSupport(String name) throws Exception{
		super(name);
	 	isMultiInput=true;
	 	inputMessageList=new String[]{"command", "endSelectInput"};
	 	initLastMsgRdMemo();  //put in initGui since the name must be set
		//Singleton
		if( obj != null ) return;
		 obj = (Drone)this;
	}
	
	/* -------------------------------------
	* Init
	* --------------------------------------
	*/
	//PREPARE INPUT THREADS
	public void initInputSupports() throws Exception{
	PlatformExpert.findInSupport( getName(), "command" ,true,view);
	}
	
 	protected void initLastMsgRdMemo(){
 			lastMsgRdMemo.put("command"+getName(),0);
 	}
	protected void initGui(){
	    env = new EnvFrame( getName(), this, new java.awt.Color(151, 228, 255), java.awt.Color.black );
	    env.init();
	    env.writeOnStatusBar(getName() + " | DroneSupport working ... ",14);
	    view = env.getOutputView();
	    initLastMsgRdMemo(); //put here since the name must be set
	 }
	
	/* -------------------------------------
	* State-based Behavior
	* -------------------------------------- 
	*/ 
	protected abstract void startMission() throws Exception;
	protected abstract void setSpeed() throws Exception;
	/* --- USER DEFINED STATE ACTIONS --- */
	/* --- USER DEFINED TASKS --- */
	/* 
		Each state acquires some input and performs some action 
		Each state is mapped into a void method 
	*/
	//Variable behavior declarations
	protected 
	String strPhoto = "";
	protected 
	String msgCommand = "";
	protected 
	String cmdName = "";
	protected 
	String cmdValue = "";
	protected 
	boolean start = false;
	protected 
	boolean stop = false;
	protected 
	boolean speed = false;
	public  java.lang.String get_strPhoto(){ return strPhoto; }
	public  java.lang.String get_msgCommand(){ return msgCommand; }
	public  java.lang.String get_cmdName(){ return cmdName; }
	public  java.lang.String get_cmdValue(){ return cmdValue; }
	public  boolean get_start(){ return start; }
	public  boolean get_stop(){ return stop; }
	public  boolean get_speed(){ return speed; }
	
	protected boolean endStateControl = false;
	protected String curstate ="state_initDrone";
	protected void stateControl( ) throws Exception{
		boolean debugMode = System.getProperty("debugMode" ) != null;
	 		while( ! endStateControl ){
	 			//DEBUG 
	 			if(debugMode) debugNextState(); 
	 			//END DEBUG
			/* REQUIRES Java Compiler 1.7
			switch( curstate ){
				case "state_initDrone" : state_initDrone(); break; 
				case "state_ready" : state_ready(); break; 
				case "state_startMission" : state_startMission(); break; 
				case "state_setspeed" : state_setspeed(); break; 
				case "state_onMission" : state_onMission(); break; 
				case "state_commandHandler" : state_commandHandler(); break; 
				case "state_endMission" : state_endMission(); break; 
			}//switch	
			*/
			if( curstate.equals("state_initDrone")){ state_initDrone(); }
			else if( curstate.equals("state_ready")){ state_ready(); }
			else if( curstate.equals("state_startMission")){ state_startMission(); }
			else if( curstate.equals("state_setspeed")){ state_setspeed(); }
			else if( curstate.equals("state_onMission")){ state_onMission(); }
			else if( curstate.equals("state_commandHandler")){ state_commandHandler(); }
			else if( curstate.equals("state_endMission")){ state_endMission(); }
		}//while
		//DEBUG 
		//if( synch != null ) synch.add(getName()+" reached the end of stateControl loop"  );
	 	}
	 	protected void selectInput(boolean mostRecent, Vector<String> tempList) throws Exception{
		Vector<IMessage> queries=comSup.prepareInput(mostRecent,getName(),
				SysKb.getSyskb(),tempList.toArray(),InteractPolicy.nopolicy() );
		//showMsg("*** queries" + queries);
		curInputMsg = selectOneInput(mostRecent,queries);	
		curInputMsgContent = curInputMsg.msgContent();	
	}
	
	protected void state_ready()  throws Exception{
		
		showMsg("----- Waiting setSpeed -----");
		curInputMsg=hl_drone_accept_command();
		curInputMsgContent = curInputMsg.msgContent();
		msgCommand =curInputMsg.msgContent() ;
		cmdName =Drone.getCommandName(msgCommand) ;
		start =cmdName.contains("setspeed") ;
		
		{//XBlockcode
		boolean _start = start;
		expXabseResult=_start;
		}//XBlockcode
		if(  (Boolean)expXabseResult ){ //cond
		curstate = "state_startMission"; 
		//resetCurVars(); //leave the current values on
		return;
		}//if cond
		showMsg("ERROR: expected 'setspeed' command to get start. Received: "+cmdName);
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void state_startMission()  throws Exception{
		
		startMission(  );hl_drone_emit_notifyStartMission( "mission started" );
		curstate = "state_setspeed"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void state_setspeed()  throws Exception{
		
		setSpeed(  );curstate = "state_onMission"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void state_onMission()  throws Exception{
		
		showMsg("exec invio_dati_sensori");
		showMsg("exec invio_foto");
		//[it.unibo.indigo.contact.impl.SignalImpl@47f3158c (name: dataSensor) (var: null), it.unibo.indigo.contact.impl.SignalImpl@280bbf3b (name: notifyStartMission) (var: null)] | command isSignal=false
		resCheck = checkForMsg(getName(),"command",null);
		if(resCheck){
			curstate = "state_commandHandler";
			return;}
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void state_commandHandler()  throws Exception{
		
		curInputMsg=hl_drone_accept_command();
		curInputMsgContent = curInputMsg.msgContent();
		cmdName =Drone.getCommandName(curInputMsgContent) ;
		cmdValue =Drone.getCommandValue(curInputMsgContent) ;
		showMsg("CMD: "+cmdName+" - VALUE: "+cmdValue);
		stop =cmdName.contains("stop") ;
		
		{//XBlockcode
		boolean _stop = stop;
		expXabseResult=_stop;
		}//XBlockcode
		if(  (Boolean)expXabseResult ){ //cond
		curstate = "state_endMission"; 
		//resetCurVars(); //leave the current values on
		return;
		}//if cond
		speed =cmdName.contains("setspeed") ;
		
		{//XBlockcode
		boolean _speed = speed;
		expXabseResult=_speed;
		}//XBlockcode
		if(  (Boolean)expXabseResult ){ //cond
		curstate = "state_setspeed"; 
		//resetCurVars(); //leave the current values on
		return;
		}//if cond
		curstate = "state_onMission"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void state_endMission()  throws Exception{
		
		/* --- TRANSITION TO NEXT STATE --- */
		resetCurVars();
		do_terminationState();
		endStateControl=true;
	}
	protected void state_initDrone()  throws Exception{
		
		showMsg("----- Drone Initialized -----");
		curstate = "state_ready"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	
   	
 	/* -------------------------------------
	* COMMUNICATION CORE OPERATIONS for drone
	* --------------------------------------
	*/
 
	protected void hl_drone_forward_photo_headQuarter( String M  ) throws Exception {
	M = MsgUtil.putInEnvelope(M);
	IMessage m = new Message("headQuarter_photo("+getName()+",photo,"+M+","+msgNum+")");
	comSup.outOnly( "headQuarter","photo",getName() , m );
	msgNum++;
	
	}
	
	protected void hl_drone_emit_dataSensor( String M  ) throws Exception {
	M = MsgUtil.putInEnvelope(M);
	IMessage m = new Message("signal("+getName()+",dataSensor,"+M+","+msgNum+")");
	comSup.outOnly( getName() ,"dataSensor",  m );
	msgNum++;
	
	}
	
	protected void hl_drone_emit_notifyStartMission( String M  ) throws Exception {
	M = MsgUtil.putInEnvelope(M);
	IMessage m = new Message("signal("+getName()+",notifyStartMission,"+M+","+msgNum+")");
	comSup.outOnly( getName() ,"notifyStartMission",  m );
	msgNum++;
	
	}
	
	protected IMessage hl_drone_accept_command(   ) throws Exception {
	//EXPERT for COMPOSED drone_accept_command isInput=true withAnswer=true applVisible=false
	IMessage answer = comSup.inOutAck(
	getName() ,"command", 
	"drone_command(ANYx1y2,command,M,N)" ); 
	return answer;
	
	}
	
	
 	/* -------------------------------------
	* CONNECTION OPERATIONS for drone
	* --------------------------------------
	*/
	
	/* -------------------------------------
	* Local body of the subject
	* --------------------------------------
	*/
	
		public void doJob() throws Exception{ stateControl(); }
	 	//INSERTED FOR DEBUG
		protected boolean nextStep = false;
		protected String stateBreakpoint = null;
		protected Vector<String> synch;
		protected synchronized void debugNextState() throws Exception{
			if( stateBreakpoint != null && ! curstate.equals(stateBreakpoint) ) return;
			while( stateBreakpoint != null && curstate.equals(stateBreakpoint) ){
				showMsg(" stateBreakpoint reached "  +  stateBreakpoint);
				synch.add("stateBreakpoint reached " + stateBreakpoint);
				//showMsg("wait");
	 			wait();			
			}
	//		if( stateBreakpoint != null   ) { //resumed!
	// 	 	stateBreakpoint = null;
	//			return;
			}
	//		while( ! nextStep ) wait();
	//		if( stateBreakpoint != null ) debugNextState();
	//		else{
	//			showMsg("resume nextStep");
	//			synch.add("nextStep done");
	//			nextStep = false;
	//		}
	//	}
	//	public synchronized void nextStateStep(Vector<String> synch) throws Exception{
	//		showMsg("nextStateStep " + curstate );
	//		this.synch = synch;
	//		nextStep = true;
	//		notifyAll();
	//	}
		public synchronized void nextStateStep(String state, Vector<String> synch) throws Exception{
			this.synch = synch;
			stateBreakpoint = state;
			nextStep = true;
			showMsg("nextStateStep stateBreakpoint=" + stateBreakpoint );
	 		notifyAll();
		}
		//END INSERTED FOR DEBUG
			
		protected void do_terminationState() throws Exception {
			//showMsg(  " ---- END STATE LOOP ---- " );
		}
	
	protected IMessage acquire(String msgId) throws Exception{
	  //showMsg("acquire "  +  msgId ); 
	  IMessage m;
	  //USER MESSAGES
	  if( msgId.equals("command")){
	  	m = hl_drone_accept_command();
	  	return m;
	  }
	 if( msgId.equals("endSelectInput")){
	  String ms = MsgUtil.bm(MsgUtil.channelInWithPolicy(InteractPolicy.nopolicy(),getName(), "endSelectInput"), 
	    getName(), "endSelectInput", "ANYx1y2", "N");
	  //Serve the auto-dispatch
	  IMessage min = core.in(new Message(ms).toString());
	  return min;
	 }
	  throw new Exception("Wrong msgId:"+  msgId);
	}//acquire	
	
	/* -------------------------------------
	* Operations (from Java)
	* --------------------------------------
	*/
	
		
	/* -------------------------------------
	* Termination
	* --------------------------------------
	*/
	public void terminate() throws Exception{ //by EndSubjectConnections
		droneForward_photo_headQuarterEnd();
		droneEmit_dataSensorEnd();
		droneEmit_notifyStartMissionEnd();
		droneAccept_commandEnd();
	 			 //Auto-forward a dispatch to finish selectInput operations
	 		    String ms =
	 		      MsgUtil.bm(MsgUtil.channelInWithPolicy(InteractPolicy.nopolicy(),getName(), "endSelectInput"), 
	 		       getName(), "endSelectInput", "endSelectInput", "0");
	 		    core.out(ms);
	if( synch != null ){
		synch.add(getName()+" reached the end of loop"  );
	}
	obj = null;
	//System.out.println(getName() + " terminated");
	}	
	// Teminate operations
	protected void droneForward_photo_headQuarterEnd() throws Exception{
		PlatformExpert.findOutSupportToEnd("drone","photo",getName(),view );
		//showMsg("terminate droneForward_photo_headQuarter");
	}
	protected void droneEmit_dataSensorEnd() throws Exception{
		//No operation is done at subject level. The SenseRemote threads are terminates 
		//when the main application is closed
	//		PlatformExpert.findOutSupportToEnd("space","coreCmd","coreToDSpace", view);		
	//		showMsg("terminate signal support");
	}
	protected void droneEmit_notifyStartMissionEnd() throws Exception{
		//No operation is done at subject level. The SenseRemote threads are terminates 
		//when the main application is closed
	//		PlatformExpert.findOutSupportToEnd("space","coreCmd","coreToDSpace", view);		
	//		showMsg("terminate signal support");
	}
	protected void droneAccept_commandEnd() throws Exception{
	 		PlatformExpert.findInSupportToEnd(getName(),"command",view );
		//showMsg("terminate droneAccept_command");
	}
}//DroneSupport
