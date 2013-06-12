package it.unibo.droneMission.system;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.droneMission.display.DisplayGuiSpeedometer;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.gauge.SpeedometerWithDisplay;
import it.unibo.droneMission.interfaces.IGaugeDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;

import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedometerPrototype {
	public SpeedometerWithDisplay speedometer=null;
	private IGaugeDisplay dis=null;
	private IBasicEnvAwt env=null;
	private TextField setspeed;
	
	public static void main(String[] args) throws Exception{
		new SpeedometerPrototype().configure();
	}
	
	protected void configure(){
		initDisplay();
		configureCkm();
	}
		
	protected void initDisplay() {
		env = new EnvFrame( "Speedometer" );
		//        env.initNoFrame();        //does not show the frame
		env.init();
		env.writeOnStatusBar("Speedometer" + " | drone sleeping ... ",14);
		dis = new DisplayGuiSpeedometer( env );
		
		setspeed = new TextField(3);
		
		Button set=new Button("Set Speed");
		set.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						speedometer.setVal(new GaugeValueInt(Integer.parseInt(setspeed.getText())));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				   
			   });

		env.addPanel(setspeed);
		env.addPanel(set);
		Button inc=new Button("Incrementa");
		inc.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						speedometer.setVal(new GaugeValueInt(speedometer.getVal().valAsInt()+Speedometer.DS));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				   
			   });
		Button dec=new Button("Decrementa");
		dec.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						speedometer.setVal(new GaugeValueInt(speedometer.getVal().valAsInt()-Speedometer.DS));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				   
			   });
		env.addPanel(inc);
		env.addPanel(dec);
	}
	protected void configureCkm(){
		env.addPanel((Panel) dis);
		speedometer=new SpeedometerWithDisplay(dis);		
	}
}
