/*
*  Generated by AN Unibo
*/
package it.unibo.contact.UAV;
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
		if( env != null ) view = env.getOutputView();
	    initLastMsgRdMemo(); //put here since the name must be set
	}
	
	/* -------------------------------------
	* State-based Behavior
	* -------------------------------------- 
	*/ 
	protected abstract void init() throws Exception;
	protected abstract void startMission() throws Exception;
	protected abstract void endMission() throws Exception;
	protected abstract java.lang.String handleCommand(java.lang.String command) throws Exception;
	protected abstract java.lang.String getWrongCommandStartReply() throws Exception;
	protected abstract boolean checkStartMission(java.lang.String command) throws Exception;
	protected abstract boolean checkEndMission() throws Exception;
	protected abstract java.lang.String getSensorsData() throws Exception;
	protected abstract java.lang.String getPicturePackage() throws Exception;
	protected abstract java.lang.String getNotifyStart() throws Exception;
	protected abstract java.lang.String getNotifyEnd() throws Exception;
	protected abstract void sleep() throws Exception;
	/* --- USER DEFINED STATE ACTIONS --- */
	/* --- USER DEFINED TASKS --- */
	/* 
		Each state acquires some input and performs some action 
		Each state is mapped into a void method 
	*/
	//Variable behavior declarations
	protected 
	boolean startMission = false;
	protected 
	boolean endMission = false;
	protected 
	String sensorsData = null;
	protected 
	String picturePackage = null;
	protected 
	String reply = null;
	protected 
	String notify = null;
	public  boolean get_startMission(){ return startMission; }
	public  boolean get_endMission(){ return endMission; }
	public  java.lang.String get_sensorsData(){ return sensorsData; }
	public  java.lang.String get_picturePackage(){ return picturePackage; }
	public  java.lang.String get_reply(){ return reply; }
	public  java.lang.String get_notify(){ return notify; }
	
	protected boolean endStateControl = false;
	protected String curstate ="st_Drone_init";
	protected void stateControl( ) throws Exception{
		boolean debugMode = System.getProperty("debugMode" ) != null;
	 		while( ! endStateControl ){
	 			//DEBUG 
	 			if(debugMode) debugNextState(); 
	 			//END DEBUG
			/* REQUIRES Java Compiler 1.7
			switch( curstate ){
				case "st_Drone_init" : st_Drone_init(); break; 
				case "st_Drone_ready" : st_Drone_ready(); break; 
				case "st_Drone_wrongStartCommand" : st_Drone_wrongStartCommand(); break; 
				case "st_Drone_startMission" : st_Drone_startMission(); break; 
				case "st_Drone_handleCommand" : st_Drone_handleCommand(); break; 
				case "st_Drone_onMission" : st_Drone_onMission(); break; 
				case "st_Drone_endMission" : st_Drone_endMission(); break; 
			}//switch	
			*/
			if( curstate.equals("st_Drone_init")){ st_Drone_init(); }
			else if( curstate.equals("st_Drone_ready")){ st_Drone_ready(); }
			else if( curstate.equals("st_Drone_wrongStartCommand")){ st_Drone_wrongStartCommand(); }
			else if( curstate.equals("st_Drone_startMission")){ st_Drone_startMission(); }
			else if( curstate.equals("st_Drone_handleCommand")){ st_Drone_handleCommand(); }
			else if( curstate.equals("st_Drone_onMission")){ st_Drone_onMission(); }
			else if( curstate.equals("st_Drone_endMission")){ st_Drone_endMission(); }
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
	
	protected void st_Drone_ready()  throws Exception{
		
		showMsg("----- Waiting setSpeed -----");
		 curRequest=hl_drone_grant_command();
		 curInputMsg= curRequest.getReceivedMessage();
		 curInputMsgContent = curInputMsg.msgContent();
		startMission =checkStartMission(curInputMsgContent) ;
		
		{//XBlockcode
		boolean _startMission = startMission;
		expXabseResult=_startMission;
		}//XBlockcode
		if(  (Boolean)expXabseResult ){ //cond
		curstate = "st_Drone_startMission"; 
		//resetCurVars(); //leave the current values on
		return;
		}//if cond
		
		{//XBlockcode
		boolean _startMission = startMission;
		boolean _operator_not = BooleanExtensions.operator_not(_startMission);
		expXabseResult=_operator_not;
		}//XBlockcode
		if(  (Boolean)expXabseResult ){ //cond
		curstate = "st_Drone_wrongStartCommand"; 
		//resetCurVars(); //leave the current values on
		return;
		}//if cond
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void st_Drone_wrongStartCommand()  throws Exception{
		
		reply =getWrongCommandStartReply() ;
		curRequest.replyToCaller( reply ); 
		showMsg("ERROR: expected 'setspeed' command to start. Received: "+curInputMsgContent);
		curstate = "st_Drone_ready"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void st_Drone_startMission()  throws Exception{
		
		reply =handleCommand(curInputMsgContent) ;
		curRequest.replyToCaller( reply ); 
		startMission(  );notify =getNotifyStart() ;
		showMsg("Notify START mission");
		hl_drone_emit_notify( notify );
		curstate = "st_Drone_onMission"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void st_Drone_handleCommand()  throws Exception{
		
		 curRequest=hl_drone_grant_command();
		 curInputMsg= curRequest.getReceivedMessage();
		 curInputMsgContent = curInputMsg.msgContent();
		reply =handleCommand(curInputMsgContent) ;
		curRequest.replyToCaller( reply ); 
		curstate = "st_Drone_onMission"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void st_Drone_onMission()  throws Exception{
		
		//[it.unibo.indigo.contact.impl.SignalImpl@2450007f (name: sensorsData) (var: null), it.unibo.indigo.contact.impl.SignalImpl@50a479 (name: notify) (var: null)] | command isSignal=false
		resCheck = checkForMsg(getName(),"command",null);
		if(resCheck){
			curstate = "st_Drone_handleCommand";
			return;}
		sensorsData =getSensorsData(  ) ;
		hl_drone_emit_sensorsData( sensorsData );
		hl_drone_emit_sensorsData( sensorsData );
		picturePackage =getPicturePackage(  ) ;
		hl_drone_forward_picturePackage_controlUnit(picturePackage );
		endMission =checkEndMission() ;
		
		{//XBlockcode
		boolean _endMission = endMission;
		expXabseResult=_endMission;
		}//XBlockcode
		if(  (Boolean)expXabseResult ){ //cond
		curstate = "st_Drone_endMission"; 
		//resetCurVars(); //leave the current values on
		return;
		}//if cond
		sleep(  );/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void st_Drone_endMission()  throws Exception{
		
		sensorsData =getSensorsData(  ) ;
		hl_drone_emit_sensorsData( sensorsData );
		hl_drone_emit_sensorsData( sensorsData );
		notify =getNotifyEnd(  ) ;
		showMsg("Notify END mission");
		hl_drone_emit_notify( notify );
		endMission(  );curstate = "st_Drone_init"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	protected void st_Drone_init()  throws Exception{
		
		showMsg("----- Drone Initialized -----");
		init(  );curstate = "st_Drone_ready"; 
		//resetCurVars(); //leave the current values on
		return;
		/* --- TRANSITION TO NEXT STATE --- */
	}
	
   	
 	/* -------------------------------------
	* COMMUNICATION CORE OPERATIONS for drone
	* --------------------------------------
	*/
 
	protected void hl_drone_forward_picturePackage_controlUnit( String M  ) throws Exception {
	M = MsgUtil.putInEnvelope(M);
	IMessage m = new Message("controlUnit_picturePackage("+getName()+",picturePackage,"+M+","+msgNum+")");
	comSup.outOnly( "controlUnit","picturePackage",getName() , m );
	msgNum++;
	
	}
	
	protected void hl_drone_emit_sensorsData( String M  ) throws Exception {
	M = MsgUtil.putInEnvelope(M);
	IMessage m = new Message("signal("+getName()+",sensorsData,"+M+","+msgNum+")");
	comSup.outOnly( getName() ,"sensorsData",  m );
	msgNum++;
	
	}
	
	protected void hl_drone_emit_notify( String M  ) throws Exception {
	M = MsgUtil.putInEnvelope(M);
	IMessage m = new Message("signal("+getName()+",notify,"+M+","+msgNum+")");
	comSup.outOnly( getName() ,"notify",  m );
	msgNum++;
	
	}
	
	protected IMessageAndContext hl_drone_grant_command(   ) throws Exception {
	//EXPERT for COMPOSED drone_grant_command isInput=true withAnswer=true applVisible=true
	IMessageAndContext answer = comSup.inOut(
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
	  	curRequest = hl_drone_grant_command();
	  	m = curRequest.getReceivedMessage();
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
		droneForward_picturePackage_controlUnitEnd();
		droneEmit_sensorsDataEnd();
		droneEmit_notifyEnd();
		droneGrant_commandEnd();
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
	protected void droneForward_picturePackage_controlUnitEnd() throws Exception{
		PlatformExpert.findOutSupportToEnd("drone","picturePackage",getName(),view );
		//showMsg("terminate droneForward_picturePackage_controlUnit");
	}
	protected void droneEmit_sensorsDataEnd() throws Exception{
		//No operation is done at subject level. The SenseRemote threads are terminates 
		//when the main application is closed
	//		PlatformExpert.findOutSupportToEnd("space","coreCmd","coreToDSpace", view);		
	//		showMsg("terminate signal support");
	}
	protected void droneEmit_notifyEnd() throws Exception{
		//No operation is done at subject level. The SenseRemote threads are terminates 
		//when the main application is closed
	//		PlatformExpert.findOutSupportToEnd("space","coreCmd","coreToDSpace", view);		
	//		showMsg("terminate signal support");
	}
	protected void droneGrant_commandEnd() throws Exception{
	 	PlatformExpert.findInSupportToEnd(getName(),"command",view );
		//showMsg("terminate droneGrant_command");
	}
}//DroneSupport
