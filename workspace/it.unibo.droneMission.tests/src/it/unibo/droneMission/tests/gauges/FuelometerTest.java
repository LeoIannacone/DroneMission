package it.unibo.droneMission.tests.gauges;

import it.unibo.droneMission.gauge.Fuelometer;
import junit.framework.TestCase;

public class FuelometerTest extends TestCase {
	protected Fuelometer fu;

	public FuelometerTest(String arg0){
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		fu = new Fuelometer();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	//invariant fuelometer
	protected boolean invariant(Fuelometer F){
		return((((F.getVal().valAsDouble()>=Fuelometer.MIN) && (F.getVal().valAsDouble()<=Fuelometer.MAX))));
	}

	public void testOnCreate(){
		assertTrue("testOnCreate",(invariant(fu) && fu.getVal().valAsDouble()==Fuelometer.MAX)); 
	}
	
	//update 
	public void testUpdate(){
		try {
			fu.update();
			assertTrue("testUpdate",(invariant(fu)));
		} catch (Exception e) {
			fail("testInc" + e.getMessage());
		}
	}
	
	//min limit
	
	public void testMinLimit(){
		try{
			while (fu.getVal().valAsDouble()>Fuelometer.MIN){
				fu.update();
			}
			fu.update();
			assertTrue("testMinLimit",(invariant(fu)));
		}
		catch (Exception e) {
			fail("TestMaxLimit"+ e.getMessage());
		}
	}
}

