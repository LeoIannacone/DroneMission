package it.unibo.droneMission.intefaces.messages;

public interface ICommand extends IMessage {
	
	// return command type
	public int getType();
	
	// command value
	public boolean hasValue();
	public int getValue();
	public void setValue(int value);
	
}
