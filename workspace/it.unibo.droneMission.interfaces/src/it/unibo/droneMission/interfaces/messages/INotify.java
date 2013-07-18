package it.unibo.droneMission.interfaces.messages;

public interface INotify extends IMessage {
	
	// get notify type
	public int getType();
	
	// message to send/receive
	public void setMessage(String message);
	public String getMessage();
	
}
