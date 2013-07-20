package it.unibo.droneMission.prototypes.messages;

import it.unibo.droneMission.interfaces.messages.ISensorData;

public class SensorData extends MessageTypeAsIntValueAsString implements ISensorData {

	protected int valueType;
	
	public SensorData(int type) {
		super(type);
	}
	
	public SensorData(int type, String value, int valueType) {
		super(type, value);
		this.valueType = valueType;
	}

	@Override
	public void setValueType(int valueType) {
		this.valueType = valueType;		
	}

	@Override
	public int getValueType() {
		return valueType;
	}
	
	

}
