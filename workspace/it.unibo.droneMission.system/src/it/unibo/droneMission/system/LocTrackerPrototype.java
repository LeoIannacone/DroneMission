package it.unibo.droneMission.system;

import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.droneMission.display.DisplayGuiCoords;
import it.unibo.droneMission.gauge.LocTrackerWithDisplay;
import it.unibo.droneMission.interfaces.IGaugeDisplay;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.is.interfaces.IBasicEnvAwt;

public class LocTrackerPrototype {
	private LocTrackerWithDisplay loctracker=null;
	private IGaugeDisplay LaDis;
	private IGaugeDisplay LoDis;
	private IBasicEnvAwt env=null;
	private TextField setlat;
	private TextField setlon;
	
	public static void main(String[] args) throws Exception{
		new LocTrackerPrototype().configure();
	}
	
	protected void configure(){
		initDisplay();
		configureLocTracker();
	}
		
	protected void initDisplay() {
		env = new EnvFrame( "Latitude", null );
		//        env.initNoFrame();        //does not show the frame
		env.init();
		env.writeOnStatusBar("Latitude" + " | drone sleeping ... ",14);
		LaDis = new DisplayGuiCoords( env );
		LoDis = new DisplayGuiCoords( env );
		
		setlat = new TextField(9);
		setlon = new TextField(9);
		
		Button set=new Button("Set Coords");
		set.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						loctracker.update((new GaugeValueDouble(Double.parseDouble(setlat.getText()))),(new GaugeValueDouble(Double.parseDouble(setlon.getText()))));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				   
			   });

		env.addPanel(setlat);
		env.addPanel(setlon);
		//continua per display lon
		
		env.addPanel(set);
		
	}
	protected void configureLocTracker(){
		env.addPanel((Panel) LaDis);
		env.addPanel((Panel) LoDis);
		loctracker=new LocTrackerWithDisplay(LaDis, LoDis);		
	}
}
