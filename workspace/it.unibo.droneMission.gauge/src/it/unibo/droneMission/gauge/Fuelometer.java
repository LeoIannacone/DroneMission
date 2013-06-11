package it.unibo.droneMission.gauge;

import java.math.BigDecimal;
import java.util.Observer;

import it.unibo.droneMission.interfaces.IFuelometer;
import it.unibo.droneMission.interfaces.IGaugeValue;
import it.unibo.droneMission.interfaces.IGaugeValueDouble;

public class Fuelometer implements IFuelometer {
	
	protected IGaugeValueDouble fuel;
	
	public Fuelometer(){
		fuel=new GaugeValueDouble(MAX);
	}

	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		if (fuel.valAsDouble() > MIN){
			//decremento carburante con arrotondamento ad una cifra decimale per visualizzazione 
			fuel.set(new BigDecimal(fuel.valAsDouble()-0.5).setScale(1 , BigDecimal.ROUND_UP).doubleValue());
		}
		else{
			//fine missione
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
		String S=""+fuel.valAsDouble();
		while(S.length()<4)
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
		return fuel;
	}

	@Override
	public void setVal(IGaugeValue value) {
		// TODO Auto-generated method stub
	
	}

}
