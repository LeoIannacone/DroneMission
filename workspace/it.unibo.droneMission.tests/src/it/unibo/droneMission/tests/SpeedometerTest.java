package it.unibo.droneMission.tests;

import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.Speedometer;
import junit.framework.TestCase;

public class SpeedometerTest extends TestCase {
	protected Speedometer sp;
	
	//variabili test
	public GaugeValueInt val=new GaugeValueInt(60);

	public SpeedometerTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		sp = new Speedometer();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	//invariant speedometer
	protected boolean invariant(Speedometer O){
		return((((O.getVal().valAsInt()>=Speedometer.MIN) && (O.getVal().valAsInt()<=Speedometer.MAX))));
	}

	public void testOnCreate(){
		assertTrue(sp.getVal().valAsInt()==Speedometer.INIT); 
	}
	
	public void testOnStartMission(){
		startMission();
		assertTrue("testOnCreate",(invariant(sp))); 
	}
	
	private void startMission() {
		// TODO Auto-generated method stub
		sp.setVal(new GaugeValueInt(60));
	}
	
	//Set value 
	public void testUpdate(){
		try {
			sp.setVal(val);
			assertTrue("testInc",(invariant(sp)));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
	
	//max limit
	
	public void testMaxLimit(){
		try{
			sp.setVal(new GaugeValueInt(Speedometer.MAX));
			sp.setVal(new GaugeValueInt(sp.getVal().valAsInt()+Speedometer.DS));
			assertTrue("testMaxLimit",(invariant(sp)));
		}
		catch (Exception e) {
			fail("TestMaxLimit"+ e.getMessage());
		}
	}
	
	//min limit
	public void testMinLimit(){
		try{
			sp.setVal(new GaugeValueInt(Speedometer.MIN));
			sp.setVal(new GaugeValueInt(sp.getVal().valAsInt()-Speedometer.DS));
			assertTrue("testMaxLimit",(invariant(sp)));
		}
		catch (Exception e) {
			fail("TestMaxLimit"+ e.getMessage());
		}
	}
}

