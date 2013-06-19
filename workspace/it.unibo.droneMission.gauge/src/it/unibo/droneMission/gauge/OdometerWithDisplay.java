package it.unibo.droneMission.gauge;

import it.unibo.droneMission.interfaces.IGaugeDisplay;
import it.unibo.droneMission.interfaces.IGaugeValue;

public class OdometerWithDisplay extends Odometer{
	protected IGaugeDisplay Dis;
	protected String displayedVal;
	
	public OdometerWithDisplay(IGaugeDisplay D){
		super();
		Dis = D;
		Dis.update(km);
		displayedVal=Dis.getCurVal();
	}
	
	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		super.update();
		Dis.update(km);
		displayedVal=Dis.getCurVal();
	}

	@Override
	public IGaugeValue getVal() {
		// TODO Auto-generated method stub
		return super.getVal();
	}

	@Override
	public String getCurValRepDisplayed() {
		// TODO Auto-generated method stub
		return displayedVal;
	}
}
