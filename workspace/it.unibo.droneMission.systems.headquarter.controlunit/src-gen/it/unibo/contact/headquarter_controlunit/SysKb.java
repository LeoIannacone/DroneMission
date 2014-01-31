package it.unibo.contact.headquarter_controlunit;
import it.unibo.is.interfaces.ISysKb;
/*
 * Singleton
 */
public class SysKb implements ISysKb{ 
private static ISysKb myself = null;
private static String curS;
	
	public static ISysKb getSyskb(){
		if( myself == null )  myself = new SysKb();
		return myself;		
	}
	
	public  boolean isSignal( String msgId ){
	return msgId.equals("sensorsData") ||
	msgId.equals("notify");
	}
	
	public static void setCurOut(String s){
		curS = s.substring(0, s.indexOf(0));
	}
	public static String getCurOut( ){
		System.out.println("	SysKb *** "+curS);
		return curS;
	}
	
}
