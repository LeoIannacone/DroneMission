package it.unibo.contact.DroneMissionSystem;
import it.unibo.is.interfaces.*;
import java.util.Vector;
import it.unibo.is.interfaces.platforms.*;
import it.unibo.contact.platformuv.LindaLike;
import it.unibo.contact.platformuv.*;
import it.unibo.is.interfaces.IMessage;
/*
*  Abstract class SubjectPassive
*/
public abstract class SubjectPassive  implements IContactSystem{

protected ILindaLike core  ;
protected IOutputView view = null;
protected boolean isMultiInput = false;
protected IBasicEnv env = null; 
protected int msgNum = 1;
 /*
 * CURRENT INPUT MESSAGE STATE
 */
	protected IMessageAndContext curRequest; //current request object (including info on caller)
	protected IAcquireOneReply curAcquireOneReply;
	protected IAcquireManyReply curAcquireManyReply;
 	protected IMessage curInputMsg = null; //current input message
 	protected IMessage curReply = null; //current reply message
 	protected String curOutMsg = "not defined msg"; //current output message
	protected Object expXabseResult;
	protected boolean pCond = false;
	protected String curDestName ="";
 /*
 * MULTI INPUT 
 */
 	protected abstract IMessage acquire(String msgId) throws Exception;
	protected Vector<IMessage> myqueries;
	protected String[] inputMessageList = null;

protected java.util.Hashtable<String,Integer> lastMsgRdMemo = 
new java.util.Hashtable<String,Integer>();	
protected String name = "";
protected final boolean distributedSpace = false;


 	public SubjectPassive() throws Exception{
	}
	
	public void setEnv(IBasicEnv env) throws Exception{
 		this.env = env;		
		initGui();
		initSupport(view);		
	}
public void setName(String name){
	this.name = name;
}
public String getName( ){
	return name;
}
public boolean isPassive(){return true;}
abstract public void doJob() throws Exception; //	stateControl( );
abstract public void terminate() throws Exception;
abstract protected void initGui() throws Exception;

protected void initSupport(IOutputView view){
    core = LindaLike.getSpace(view);
}

protected boolean isSignal( String msgId ){
	return msgId.equals("dataSensor");		 	  
}
 
 /* ==================================
	UTILS
 ================================== */
 	protected void resetCurVars(){
		curRequest = null;
		curAcquireOneReply  = null;
		curAcquireManyReply = null;
		curInputMsg = null;
		curReply = null;
		curOutMsg = "not defined msg";
		expXabseResult = null;
		pCond = false;
		curDestName ="";
	}
 
 	protected void resetLocalGlobalTime(){
 		showMsg("------- reset local and global time (if DSPace) -----"  );
		lastMsgRdMemo.put("sSignal"+getName(), 0 );
	}
  	protected void resetLocalTime(){
		showMsg("------- reset local time -----"  );
		lastMsgRdMemo.put("sSignal"+getName(), 0 );
 	}
  	protected void resetGlobalTime(){
	}
 
//*** SUBJECT COMMUNICATION  SUPPORT *** 
protected CommLogic comSup = new CommLogic(view);

//Convert a string into a IMessage
protected IMessage cvtToMessage(String msg) throws Exception{
		IBasicMessage mb = new BasicMessage(msg);
	IMessage m = new Message(mb.getMsgContent());
	return m;
}
protected IMessage cvtToApplMessage(String msg) throws Exception{
	IMessage m = new Message( msg );
	return m;
}

//Check if a message with a given msgId is present in the SharedSpace
protected boolean checkForMsg( String receiver, String msgId, IPolicy policy  ) throws Exception{
	int lastMsgNum = lastMsgRdMemo.get(msgId+receiver);
	//showMsg("lastMsgNum  for " +msgId + " = " +  lastMsgNum );
	IMessage query = new Message(
			 MsgUtil.bm( MsgUtil.channelInWithPolicy(policy,receiver, msgId), 
			 	"SOURCE", msgId, "ANY", "N")) ;
	IMessage inMsg = core.rd( lastMsgNum, query.toString() );
	return inMsg != null;	
}

protected boolean checkForSignal(String sender, String msgId, IPolicy policy ) throws Exception{
//showMsg("dentro check signal "+msgNum);
int lastMsgNum = lastMsgRdMemo.get(msgId+sender);
showMsg("lastMsgNum  for " +msgId + " = " +  lastMsgNum );
IMessage query = new Message(
	MsgUtil.bm( msgId, "SOURCE", msgId, "ANY", "N") 
);
IMessage inMsg = core.rd(lastMsgNum,query.toString());
return inMsg != null;
}

//Check if a message is sent by the platform
protected boolean isInternalMsg(String msg){
    return ( msg.startsWith("IN") || msg.startsWith("RD") ||
        msg.startsWith("RW") ||  msg.startsWith("OM") );
}
//Check if a message is sent by a subject
protected IMessage getMessageIfSentBy( String[] emitters, String msg ){
 try {
 	if( isInternalMsg(msg) ) return null;
	IMessage m = cvtToMessage(msg);		         
	if(  m.toString().startsWith("outgoing") ) 
		m = cvtToApplMessage( m.msgEmitter() );//convert arg0 
	for( int i=0; i<emitters.length; i++ ){
		if( m.msgEmitter().equals( emitters[i] )){ 
			//showMsg(  " " + m + " " + Thread.currentThread() );
			return m;
		}
	}		        
	return null;
 } catch (Exception e) {
 	showMsg(  " *** Error:" +  e.getMessage()  );
	return null;
 }				
}
//Returns an application message (if any)
 protected IMessage getApplicationMessage( String msg ){
 	//showMsg(  "	¤¤¤)  " + msg );
	try {
		if ( isInternalMsg(msg)  ) return null;
 		//showMsg(  "	¤¤¤)  " + m + Thread.currentThread());
		IMessage m = cvtToMessage(msg);
		if(  msg.contains( "outgoing" ) ) {
			m = cvtToApplMessage( m.msgEmitter() );//convert arg0 
		}
		if(m.msgId().equals("endSelectInput")) return null;
		//message sent by the observer itself are non interesting
		if( m.msgEmitter().equals( getName() )) return null; 
		//showMsg(  "	¤¤¤)  " + m  + " " + Thread.currentThread() );
		return m;
	} catch (Exception e) {
		showMsg(  "	*** E) " +  e.getMessage()  );
		return null;
	}				
 }
 
 
 /*
 * MULTI INPUT UTILITIES
 */
	
	protected Vector<IMessage> prepareInput(boolean mostRecent) throws Exception {
		return comSup.prepareInput(mostRecent, getName(),
				SysKb.getSyskb(),inputMessageList,InteractPolicy.nopolicy() );
	}
	
	 protected IMessage selectOneInput( boolean mostRecent, Vector<IMessage> queries ) throws Exception {
	 ISysKb kb = SysKb.getSyskb();
	  IMessage m = comSup.selectOneMessage(mostRecent, getName(),kb,lastMsgRdMemo,queries);
	  if( kb.isSignal( m.msgId() )) return m;
	  else return acquire( m.msgId() )  ;
	 }
 
	 protected IMessage selectOneInput( ) throws Exception{
		 if( myqueries == null ) myqueries = prepareInput( false ); 
		 return selectOneInput(  false,myqueries );
	 }

	 protected IMessage selectOneInput(boolean mostRecent) throws Exception{
		 if( myqueries == null ) myqueries = prepareInput( mostRecent);
		 return selectOneInput(  mostRecent,myqueries );
	 }

	protected IMessage selectWithPriority(boolean mostRecent, String[] ordereMsgdList) throws Exception{
		Vector<IMessage> orderedQueries = comSup.prepareInput( mostRecent,
				  getName(),SysKb.getSyskb(),ordereMsgdList,InteractPolicy.nopolicy() );	
		return selectOneInput(   mostRecent,orderedQueries );
	} 

		
/*
* SHOW UTILITIES
*/

	public  void showMsg( String m){
		MsgUtil.showMsg(view, this.getName() + " " + m );
	}
	
	public void showMsg( IMessage m){
		showMsg("msg: ||" +  m.msgContent() + "|| from " + m.msgEmitter() );
	}
	
	public  void showMessage( String comment,  IMessage m){
	if( m != null) {
		String msgEmitter 	= m.msgEmitter();
		String msgId 		= m.msgId();
		String msgContent 	= m.msgContent();
		String msgNum 		= m.msgNum();

		MsgUtil.showMsg(view, this.getName() + " " + comment + ": " + m + "\n\t" +
			"msgEmitter="+ msgEmitter  + " msgId="+ msgId +
			" msgContent=" + msgContent + " msgNum=" + msgNum);
	}		  
	}  
	
  	
}		
