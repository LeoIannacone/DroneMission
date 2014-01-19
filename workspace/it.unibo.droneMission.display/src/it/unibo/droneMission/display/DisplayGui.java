package it.unibo.droneMission.display;

import it.unibo.droneMission.interfaces.gauges.IDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;

import java.awt.Panel;
import java.awt.TextField;
import java.util.Observable;

public class DisplayGui extends Panel implements IDisplay{
	private static final long serialVersionUID = 1L;
	protected IBasicEnvAwt env;
	protected TextField tarea;
	protected String curValToShow = "not yet initialized";
	
	public DisplayGui( IBasicEnvAwt env ){
		   tarea = new TextField(6);
		   tarea.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,24));
		   add(tarea);
		   env.addPanel(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCurVal() {
		// TODO Auto-generated method stub
		return curValToShow;
	}

	@Override
	public void setCurVal(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String val) {
		// TODO Auto-generated method stub
        curValToShow = val;
        tarea.setText(curValToShow);
	}

}
