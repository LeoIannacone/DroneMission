package it.unibo.droneMission.display;

import java.util.Observable;

import it.unibo.droneMission.interfaces.gauges.IDisplay;

public class DisplayStandardOut implements IDisplay{
	
	private String showVal;
	
	public DisplayStandardOut(){
		showVal="Not yet initialized";
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCurVal() {
		// TODO Auto-generated method stub
		return showVal;
	}

	@Override
	public void setCurVal(String value) {
		// TODO Auto-generated method stub
		showVal=""+value;
		System.out.println(showVal);
	}

	@Override
	public void update(String val) {
		// TODO Auto-generated method stub
		
	}
	

}
