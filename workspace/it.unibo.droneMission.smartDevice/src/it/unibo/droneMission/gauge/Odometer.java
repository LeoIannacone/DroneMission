package it.unibo.droneMission.gauge;
import java.util.Observer;

import it.unibo.droneMission.interfaces.gauges.*;


public class Odometer implements IOdometer{
	protected IGaugeValueInt km; //chilometri percorsi
	
	public Odometer(){
		km=new GaugeValueInt(INIT);
	}
	
	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		if (km.valAsInt() == MAX){
			km.set(INIT);
		}
		else{
			km.set(km.valAsInt()+1);
		}
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
		String S=""+km.valAsInt();
		while(S.length()<6)
			S="0"+S;
		return S;
	}

	@Override
	public void setCurValRepDisplayed(String value) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public IGaugeValue getVal() {
		// TODO Auto-generated method stub
		return km;
	}

	@Override
	public void setVal(IGaugeValue value) {
		km=new GaugeValueInt(value.valAsInt());
	}
	

}
