package it.unibo.droneMission.tests;

import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.IGaugeValueInt;
import junit.framework.TestCase;

public class SpeedometerTest extends TestCase {
	protected Speedometer sp;

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
		sp.setVal(new GaugeValueInt(80));
	}

	//Set value 
	public void testUpdate(IGaugeValueInt val){
		try {
			sp.setVal(val);
			assertTrue("testInc",(invariant(sp)));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
}
