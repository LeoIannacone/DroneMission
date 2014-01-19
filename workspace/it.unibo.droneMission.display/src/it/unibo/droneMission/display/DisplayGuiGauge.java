package it.unibo.droneMission.display;

import it.unibo.droneMission.interfaces.gauges.IGaugeDisplay;
import it.unibo.droneMission.interfaces.gauges.IGaugeValue;
import it.unibo.is.interfaces.IBasicEnvAwt;

public class DisplayGuiGauge extends DisplayGui implements IGaugeDisplay{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisplayGuiGauge(IBasicEnvAwt env) {
		super(env);
	}
	public void update(String v) {

	}
	protected boolean checkVal(String v){
	       if( v.length() != 6 ) return false;
	       for( int i=0; i<v.length()-1; i++){
	               if( ! isDigit( v.charAt(i) ) ) return false;
	       }
	       return true;
	}
	protected boolean checkDigitVal(String v){
	       for( int i=0; i<v.length()-1; i++){
	               if( ! isDigit( v.charAt(i) ) ) return false;
	       }
	       return true;
	}

	protected boolean isDigit(char ch){
		return (ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || 
				ch=='5' || ch=='6' || ch=='7' || ch=='8' || ch=='9' || 
				ch=='.' || ch=='-');
	}
	@Override
	public String getDisplayedVal() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDisplayedVal(String value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(IGaugeValue v) {
		// TODO Auto-generated method stub
		String val=v.valAsString();
		boolean Ck = checkVal(val) ;
		if(Ck){
			super.update(val);
		}
		else 
			if( checkDigitVal(val) ){
				super.update( v.valAsString() );
			}else
		        curValToShow = "";
	}
}