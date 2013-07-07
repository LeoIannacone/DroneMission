package it.unibo.droneMission.system;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.droneMission.display.DisplayGuiOdometer;
import it.unibo.droneMission.gauge.FuelometerWithDisplay;
import it.unibo.droneMission.interfaces.IGaugeDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuelometerPrototype {
	public FuelometerWithDisplay fuelometer=null;
	private IGaugeDisplay dis=null;
	private IBasicEnvAwt env=null;
	
	public static void main(String[] args) throws Exception{
		new FuelometerPrototype().configure();
	}
	
	protected void configure(){
		initDisplay();
		configureCkm();
	}
		
	protected void initDisplay() {
		env = new EnvFrame( "Fuelometer", null );
		//        env.initNoFrame();        //does not show the frame
		env.init();
		env.writeOnStatusBar("Fuelometer" + " | working ... ",14);
		dis = new DisplayGuiOdometer( env );
		
		Button b=new Button("Consuma");
		b.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						fuelometer.update();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				   
			   });
		env.addPanel(b);
	}
	protected void configureCkm(){
		env.addPanel((Panel) dis);
		fuelometer=new FuelometerWithDisplay(dis);		
	}
}

