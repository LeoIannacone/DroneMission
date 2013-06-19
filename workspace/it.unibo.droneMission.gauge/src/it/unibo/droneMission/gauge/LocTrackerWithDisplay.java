package it.unibo.droneMission.gauge;

import it.unibo.droneMission.interfaces.IGaugeDisplay;
import it.unibo.droneMission.interfaces.IGaugeValue;

public class LocTrackerWithDisplay extends LocTracker{
	protected IGaugeDisplay LonDisplay;
	protected IGaugeDisplay LatDisplay;
	protected String LonDisplayedVal;
	protected String LatDisplayedVal;
	
	public LocTrackerWithDisplay(IGaugeDisplay D1, IGaugeDisplay D2){
		super();
		LatDisplay=D1;
		LonDisplay=D2;
		LatDisplay.update(latitude);
		LatDisplayedVal=LatDisplay.getCurVal();
		LonDisplay.update(longitude);
		LonDisplayedVal=LonDisplay.getCurVal();
	}
	
	public void update(IGaugeValue lat, IGaugeValue lon) {
		// TODO Auto-generated method stub
		super.update(lat, lon);
		if ((check(lat))&&(check(lon))){
			LatDisplay.update(lat);
			LatDisplayedVal=LatDisplay.getCurVal();
			LonDisplay.update(lon);
			LonDisplayedVal=LonDisplay.getCurVal();
		}
	}

	
	public String getCurValRepDisplayed() {
		// TODO Auto-generated method stub
		return (super.getLat().toString()+","+super.getLon().toString());
	}
}
