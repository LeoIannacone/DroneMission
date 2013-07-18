/*
*  Generated by AN Unibo
*/
package it.unibo.contact.platformuv;
import it.unibo.is.interfaces.*;
import it.unibo.is.interfaces.platforms.ILindaLike;
import it.unibo.is.interfaces.platforms.IMessageAndContext;
import it.unibo.is.interfaces.protocols.IConnInteraction;
import java.util.Vector;

public class ConnInputReceiver extends Thread{
	protected ILindaLike core;
	protected IConnInteraction conn;
	protected String name;
	protected boolean debug = false;
	protected String curSender;
	protected String curChannel;
	protected boolean goon = true;
	protected String receivedMsg;
	protected IOutputView view;
	protected boolean isTwoWay = true;
    protected Vector<String> mexs = new Vector<String>();
    protected ConnProtOut connOut ;
    protected static Vector<ConnInputReceiver> isInRawMode = new Vector<ConnInputReceiver>();
    
	public ConnInputReceiver( 
				String name, IConnInteraction conn, IOutputView view   ) throws Exception {
			this.name 			= name;
 			this.conn 			= conn;
  			this.view			= view;
			setName(name);
			core  				= LindaLike.getSpace();
			println("**** ConnInputReceiver CREATED  *** " + name);
			if( System.getProperty("connTrace") != null ) 
				debug = System.getProperty("connTrace").equals("set") ;
	}
	public ConnInputReceiver( ConnProtOut connOut,
			String name, IConnInteraction conn, IOutputView view   ) throws Exception {
			this.connOut		= connOut;
			this.name 			= name;
			this.conn 			= conn;
			this.view			= view;
		setName(name);
		core  				= LindaLike.getSpace();
		println("**** ConnInputReceiver  *** " + name + " CREATED  WITH ConnProtOut=" + connOut);
		if( System.getProperty("connTrace") != null ) 
			debug = System.getProperty("connTrace").equals("set") ;
	}


	public void run() {
		println( "STARTS " );
		doJob();
		//If doJob terminates send an exception to all the other connected nodes
		for( int i = 0; i<mexs.size(); i++){
			//System.out.println( " %%% ConnInputReceiver emits exception " + mexs.elementAt(i)  );
			try {
				core.out(mexs.elementAt(i));
			} catch (Exception e) {
 				//e.printStackTrace();
 				println("ERROR " + e.getMessage() );
			}
		}
		println( "ENDS " + name + " " + mexs.size());
	}		

	protected void memo(){
		isInRawMode.add(this);
	}
	protected void unmemo(){
		isInRawMode.remove(this);
	}	
	public static void resumeNormalMode(){
		if( isInRawMode.size() > 0 )
		isInRawMode.get(0).resumeHighLevel();
	}
			
	/*
	 * This is a single thread for all the messages using the conn
	 */
	protected synchronized void receiveHighLevel() throws Exception{
		receivedMsg = conn.receiveALine( );  
//		println("received  " + receivedMsg);
		while( receivedMsg == null ){ //this means that the connection works in raw mode
			//System.out.println("ConnInputReceiver waiting ... " + conn );
			memo();
  			wait();
 			//System.out.println("ConnInputReceiver resuming ... " + conn );
			receivedMsg = conn.receiveALine( );  
		}	
		unmemo();	
	}
	
	public synchronized void resumeHighLevel(){
		notifyAll();
	}
	
	protected void doJob(){
		try {
	 		while(goon){ 
//			println("receiving a new msg isTwoWay=" + isTwoWay + " *** ");
			receiveHighLevel();
 			IMessage mm = new Message(receivedMsg);
//			println("received " + mm );
			//A msgAnswer must simply stored in the sharedSpace
			String answer = chekForAnswer(receivedMsg,mm);
			if( answer != null ){
//				doprintln("received THE ANSWER:" + receivedMsg  );
				core.out( answer );
			} else{
				boolean withAnsw = RunTimeKb.getInputConnMsg( mm.msgId() );
// 				doprintln("received " + receivedMsg + " WITH ANSWER=" + withAnsw);
				receiveAndStore( mm,withAnsw );	//can raise and Exception noMessage
			}
		}
		} catch (Exception e) {
			println( "WARNING:" + e.getMessage() + " ... going to terminate" );
			try {
				conn.closeConnection();
				//Generate an exception message to terminate the Sensor AcquireReply Thread 
				core.out( "coreToDSpace_space_coreCmd(space,coreCmd,'exception',N)" );		
			} catch (Exception e1) {
				println( "ERROR " + e1  );
			}
			goon = false;
		}
		println("ends ConnInputReceiver" );		
	}
 
	protected String chekForAnswer( String receivedMsg, IMessage m  ) throws Exception{
		String outMsg = null;
 		String channelId = m.getMsgName();
		int i1 = channelId.indexOf('_');
		int i2 = channelId.lastIndexOf('_');
		if( (i1 > 0) && (i2 > 0) && (i2 > i1) ){ //the msg is an answer
			println(receivedMsg + " IS AN ANSWER "   );			
			if( receivedMsg.startsWith("proxy"))
				outMsg = receivedMsg.replaceFirst("proxy", "");
			else
				outMsg = receivedMsg;			
 		}
		return outMsg;
	}
	/*
	* Stores the msg the sharedSpace acting as a proxy.
	* If the channel is two-way (withAnswer==true)
	* it waits for the reply from the application
	* and then sends the answer to the caller
	*/
	protected void receiveAndStore( IMessage mm, boolean withAnswer ) throws Exception{
		if(  withAnswer ){
		String mex = MsgUtil.buildExceptionReplyTerm( ""+mm,"exception(connection for "+ mm.msgEmitter() + ")");
		//System.out.println("+++ adding in mexs:" + mex  + " of " + name);
		mexs.addElement( mex );
			IMessage mp = new Message( proxyMsg(mm) );
			println("receiveAndStore wirhAnswer " + mp );
			core.out( ""+mp );
			RunTimeKb.addSubjectInConnSupport(name, mp.msgId(), conn );
 			new WaitAnswerThread( conn,mp ).start();
		}else{
		//Since there is no answer to send, no proxy is needed
			println("receiveAndStore no abswer " + mm );
			core.out( ""+mm );			
		}
	}
		
 	protected String proxyMsg(IMessage m) throws Exception{
		curSender 	= m.msgEmitter();
		curChannel 	= m.getMsgName();
		String msgId = m.msgId();
		String msgContent = m.msgContent();
		String msgNum = m.msgNum();
		String proxyMsg = curChannel+"(proxy"+curSender+","+msgId+","+msgContent+","+msgNum+")";
		//println("proxyMsg " + proxyMsg );
		return proxyMsg;
	}
 		
		
	protected void doprintln( String msg){
 		String m = "    *%%%* ConnInputReceiver "+name+ "| "  + msg;
		if( view != null ) view.addOutput( m );
		else System.out.println(m);		
	}

	protected void println( String msg){
 	 	if( debug )
			doprintln(msg);
	}

/* 
 * -------------------------------------------
 * WaitAnswerThread
 * -------------------------------------------
 */
	protected class WaitAnswerThread extends Thread{
 	protected IMessage mm;
 	protected String curSender;
 	protected String curChannel;
 	protected String msgId;
 	protected String msgNum;
 	protected  IConnInteraction conn;
 	
		public WaitAnswerThread(IConnInteraction conn,IMessage mm){
			this.conn  = conn;
			this.mm    = mm;
 			curSender  = mm.msgEmitter();
 			curChannel = mm.getMsgName();
 			msgId = mm.msgId();
 			msgNum = mm.msgNum();
		}
		
		public void run(){
		 try {
 			String query = ""+curSender+"_"+curChannel+"(X,"+msgId+",Z,"+msgNum+")";
			println("STARTS with query=" + query);
			boolean goon = true;
			while(goon){
				IMessage receivedMsg = core.in(query );
				String outMsg = receivedMsg.getMsgContent();
				if( outMsg.startsWith("proxy")) outMsg = outMsg.replaceFirst("proxy", "");
	 			println("sending " + outMsg  );
/*	 			
	 			if( outMsg.contains("endOfAnswerSysMsg") ){
	 				goon = false;
	 			} else if( outMsg.contains(IMessageAndContext.endMessage)){
	 				goon = false;
	 				//conn.sendALine(outMsg);
	 				break;
	 			}
*/
	 			if( outMsg.contains(IMessageAndContext.endMessage)){
	 				goon = false;
  	 			}	 			
				conn.sendALine(outMsg);
			}
			println("ENDS "  );
		 } catch (Exception e) {
			 goon = false;
			 System.out.println("WaitAnswerThread "+ mm + " error =" + e.getMessage() );
		 }
		}
		
 		
	protected void doprintln( String msg){
		String m = "    *%%%* WaitAnswerThread "+ curChannel + " " + msg;
		if( view != null ) view.addOutput( m );
		else System.out.println(m);		
	}

	protected void println( String msg){
 	 	if( debug ) 
	 		doprintln(msg);
	}
	
	}//WaitAnswerThread	

}

