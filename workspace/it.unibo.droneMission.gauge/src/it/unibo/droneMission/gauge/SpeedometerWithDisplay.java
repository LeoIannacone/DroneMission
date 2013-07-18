package it.unibo.droneMission.gauge;

import it.unibo.droneMission.interfaces.gauges.IGaugeDisplay;
import it.unibo.droneMission.interfaces.gauges.IGaugeValue;

public class SpeedometerWithDisplay extends Speedometer {
	protected IGaugeDisplay Dis;
	protected String displayedVal;
	
	public SpeedometerWithDisplay(IGaugeDisplay D){
		super();
		Dis = D;
		Dis.update(speed);
		displayedVal=Dis.getCurVal();
	}
	
	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub

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

	@Override
	public void setVal(IGaugeValue value) {
		// TODO Auto-generated method stub
		super.setVal(value);
		Dis.update(speed);
		Dis.getCurVal();
	}
	
}