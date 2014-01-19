package it.unibo.droneMission.tests.gauges;

import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.LocTracker;

import junit.framework.TestCase;

public class LocTrackerTest extends TestCase {
	protected LocTracker lt;

	public LocTrackerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		lt = new LocTracker();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	//invariant odometer
	protected boolean invariant(LocTracker L){

		return((((L.getLat().valAsDouble()>=LocTracker.MIN) &&
				 (L.getLat().valAsDouble()<=LocTracker.MAX)) &&
				 ((L.getLon().valAsDouble()>=LocTracker.MIN) && 
				 (L.getLon().valAsDouble()<=LocTracker.MAX))
			  ));
	}

	public void testOnCreate(){
		assertTrue("testOnCreate",(invariant(lt))); 
	}
	
	//update 
	public void testUpdate(){
		try {
			lt.update(new GaugeValueDouble(LocTracker.MAX), new GaugeValueDouble(LocTracker.MAX));
			assertTrue("testInc",(invariant(lt)));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
}
