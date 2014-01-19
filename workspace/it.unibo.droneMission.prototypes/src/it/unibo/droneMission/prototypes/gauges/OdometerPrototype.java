package it.unibo.droneMission.prototypes.gauges;
import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.droneMission.display.DisplayGuiOdometer;
import it.unibo.droneMission.gauge.OdometerWithDisplay;
import it.unibo.droneMission.interfaces.gauges.IGaugeDisplay;
import it.unibo.is.interfaces.IBasicEnvAwt;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OdometerPrototype {
	public OdometerWithDisplay odometer=null;
	private IGaugeDisplay dis=null;
	private IBasicEnvAwt env=null;
	
	public static void main(String[] args) throws Exception{
		new OdometerPrototype().configure();
	}
	
	protected void configure(){
		initDisplay();
		configureCkm();
	}
		
	protected void initDisplay() {
		env = new EnvFrame( "Odometer", null );
		//        env.initNoFrame();        //does not show the frame
		env.init();
		env.writeOnStatusBar("Odometer" + " | working ... ",14);
		dis = new DisplayGuiOdometer( env );
		
		Button b=new Button("Incrementa");
		b.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						odometer.update();
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
		odometer=new OdometerWithDisplay(dis);		
	}
}

