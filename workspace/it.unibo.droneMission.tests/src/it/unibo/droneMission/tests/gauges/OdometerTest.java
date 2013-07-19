package it.unibo.droneMission.tests.gauges;

import it.unibo.droneMission.gauge.Odometer;
import junit.framework.TestCase;


public class OdometerTest extends TestCase {
	protected Odometer od;

	public OdometerTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		od = new Odometer();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	//invariant odometer
	protected boolean invariant(Odometer O){
		return((((O.getVal().valAsInt()>=Odometer.INIT) && (O.getVal().valAsInt()<=Odometer.MAX))));
	}

	public void testOnCreate(){
		assertTrue("testOnCreate",(invariant(od) && od.getVal().valAsInt()==Odometer.INIT)); 
	}
	
	//Increment 
	public void testUpdate(){
		try {
			od.update();
			assertTrue("testInc",(invariant(od) && od.getVal().valAsInt()==Odometer.INIT+1));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
	
	//Increment at limit 
	public void testAtLimit() {
		try {
			incAtLimit();
			od.update();
			assertTrue("testAtLimit",(od.getVal().valAsInt()==Odometer.INIT));
		} catch (Exception e) {
			fail("testAtLimit" + e.getMessage());
		}
	}
	protected void incAtLimit() {
		try {
			for(int i=1;i<=Odometer.MAX;i++){
				od.update();
			}
			assertTrue("incAtLimit", invariant(od));
		} catch (Exception e) {
			fail("incAtLimit"+ e.getMessage());
		}
	}
}
