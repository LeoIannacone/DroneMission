/*
*  Generated by AN Unibo
*/
package it.unibo.contact.platformuv;
import it.unibo.is.interfaces.IMessage;
import java.util.Vector;
import it.unibo.is.interfaces.platforms.IMessageAndContext;

public class Timer extends Thread{
protected String client = null;
protected int alarmTime=0;
protected String msgIn;
protected int nQuery ;
protected int numOfAnswerAcquired;
protected Vector<IMessage> answers;

	public Timer(int nQuery, String msgIn, Vector<IMessage> answers, int time) throws Exception {
		this.nQuery = nQuery;
		this.msgIn = msgIn;
		this.answers = answers;
		this.numOfAnswerAcquired = numOfAnswerAcquired;
		alarmTime = time;
		start();
 	}

 	public void run()  {
		//println("STARTS");
 		try {
  			Thread.sleep(alarmTime);
 			//println(" --- Timer RESUMING " + msgIn);
	        if( answers.size() < nQuery){ //no message received
		        String exMsg = MsgUtil.buildExceptionTerm(msgIn,"exception(timeOut)" );
	        	LindaLike.getSpace().out(exMsg); 	
    			String outMsg = MsgUtil.buildExceptionTerm(msgIn , IMessageAndContext.endMessage );
	        	LindaLike.getSpace().out(outMsg); 	
	        }
  		} catch (Exception e) {
			e.printStackTrace();
		}
 		//println("ENDS");
 	}
		 	
	protected void println(String msg){
		System.out.println(msg);
	}
	
}//Timer