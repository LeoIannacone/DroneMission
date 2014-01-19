package it.unibo.droneMission.gauge;

import java.util.Observer;

import it.unibo.droneMission.interfaces.gauges.IGaugeValue;
import it.unibo.droneMission.interfaces.gauges.IGaugeValueInt;
import it.unibo.droneMission.interfaces.gauges.ISpeedometer;

public class Speedometer implements ISpeedometer{

	protected IGaugeValueInt speed;
	
	public Speedometer(){
		speed=new GaugeValueInt(INIT);
	}
	
	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCurValRepDisplayed() {
		// TODO Auto-generated method stub
		String S=""+speed.valAsInt();
		while(S.length()<3)
			S="0"+S;
		S = S + " km/h";
		return S;
	}

	@Override
	public void setCurValRepDisplayed(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGaugeValue getVal() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public void setVal(IGaugeValue value) {
		// TODO Auto-generated method stub
		if ((value.valAsInt()>=Speedometer.MIN)&&(value.valAsInt()<=Speedometer.MAX)){
			speed.set(value.valAsInt());
		}
		else if(value.valAsInt()<Speedometer.MIN)
			speed.set(Speedometer.MIN);
		else if(value.valAsInt()>Speedometer.MAX)
			speed.set(Speedometer.MAX);
	}

}
