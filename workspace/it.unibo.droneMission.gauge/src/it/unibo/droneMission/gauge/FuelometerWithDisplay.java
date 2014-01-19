package it.unibo.droneMission.gauge;

import it.unibo.droneMission.interfaces.gauges.IGaugeDisplay;
import it.unibo.droneMission.interfaces.gauges.IGaugeValue;

public class FuelometerWithDisplay extends Fuelometer {
	protected IGaugeDisplay Dis;
	protected String displayedVal;
	
	public FuelometerWithDisplay(IGaugeDisplay D){
		super();
		Dis = D;
		Dis.update(fuel);
		displayedVal=Dis.getCurVal();
	}
	
	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		super.update();
		Dis.update(fuel);
		Dis.getCurVal();
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
