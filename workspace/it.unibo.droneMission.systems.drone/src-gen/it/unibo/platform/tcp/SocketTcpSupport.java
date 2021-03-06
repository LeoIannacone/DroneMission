/*
*  Generated by AN Unibo
*/
package it.unibo.platform.tcp;
  
import it.unibo.is.interfaces.IOutputView;
import it.unibo.is.interfaces.protocols.ITcpConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SocketTcpSupport implements ITcpConnection{
private boolean debug = false;
private String logo;
private static HashMap<String,ServerSocket> activePorts = 
							new HashMap<String,ServerSocket>();
private IOutputView view = null;

	public SocketTcpSupport(String logo,IOutputView view) {
		this.logo = logo ;
		this.view = view ;
		if( System.getProperty("tcpLowTrace") != null ) 
			debug = System.getProperty("tcpLowTrace").equals("set") ;
	}

	
	/**
	 * To be used by a receiver to start a connection.
	 */
	public ServerSocket connectAsReceiver(int portNum) throws Exception{
		println( "connectAsReceiver on port " + portNum );
		if( activePorts.get(""+portNum) != null ){ //port already active
			println(" +++ INPUT PORT " + portNum +" ALREADY ACTIVE");
			return activePorts.get(""+portNum);
		}
		ServerSocket serverSocket = new ServerSocket( portNum );	
		activePorts.put(""+portNum, serverSocket);
		return serverSocket;
	}
	
	public Socket acceptAConnection(ServerSocket serverSocket) throws Exception {
		int timeOut = 300000;
		if( System.getProperty("inputTimeOut") != null )
			timeOut = Integer.parseInt(
					System.getProperty("inputTimeOut"));
		serverSocket.setSoTimeout(timeOut); //wait for timeOut sec
		Socket socket = serverSocket.accept();
		println( " *** has accepted a connection ... " + socket.getRemoteSocketAddress());
		return  socket;
	}
	
	public Socket connectAsClient(String hostName, int port) throws Exception{
//		println( "connecting as client to " + hostName + " " + port);
		try{
			Socket socket = new Socket( hostName, port );	//bloccante
//			println( "connected a client to " + hostName );
			return  socket;
		}catch(Exception e){
//			println( "ERROR " + e.getMessage() );
			throw e;
		}
	}
		
 	
	public  void closeConnection(ServerSocket serverSocket) throws Exception{
		System.out.println( "closeConnection tcp " + serverSocket );
		serverSocket.close();
	}

 
	protected  void println( String msg){
		 if( debug ) 
			 doprintln("		+++ SocketTcpSupport|" + logo + " " + msg  );
	}

	protected  void doprintln( String msg){
		if( view != null )
			view.addOutput(msg);
		else System.out.println(msg  );		
	}


}
