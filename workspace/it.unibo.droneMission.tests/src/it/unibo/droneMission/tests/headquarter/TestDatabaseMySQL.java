package it.unibo.droneMission.tests.headquarter;

import it.unibo.droneMission.gauge.Fuelometer;
import it.unibo.droneMission.gauge.GaugeValueDouble;
import it.unibo.droneMission.gauge.GaugeValueInt;
import it.unibo.droneMission.gauge.Odometer;
import it.unibo.droneMission.gauge.Speedometer;
import it.unibo.droneMission.interfaces.headquarter.IDataBase;
import it.unibo.droneMission.interfaces.headquarter.IStorage;
import it.unibo.droneMission.interfaces.messages.ICommand;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.IReply;
import it.unibo.droneMission.prototypes.messages.Command;
import it.unibo.droneMission.prototypes.messages.File;
import it.unibo.droneMission.prototypes.messages.Notify;
import it.unibo.droneMission.prototypes.messages.PicturePackage;
import it.unibo.droneMission.prototypes.messages.Reply;
import it.unibo.droneMission.prototypes.messages.SensorsData;
import it.unibo.droneMission.systems.headquarter.FactoryStorage;

public class TestDatabaseMySQL {

	private static IStorage db;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			db = FactoryStorage.getInstance("mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.setDebug(3);
		
//		testGetLatestPicturePackage();
		testGetLatestPicturePackage();
//		testStoreNotify();
//		testStoreNotify();
//		testStoreNotify();
//		testGetLatestNotify();
		
	}
	
	public static void testStoreNotify() {
		Notify n = new Notify(1, "This is a notify");
		db.storeNotify(n);
	}
	
	public static void testGetLatestNotify() {
		INotify n = db.getLatestNotify();
		System.out.println(n.toJSON());
	}
	
	public static void testGetLatestPicturePackage() {
		IPicturePackage p = db.getLatestPicturePackage();
		System.out.println(p.toJSON());
	}
	
	public static void testStoreCommand() {
		ICommand command = new Command(10, 3);
		db.storeCommand(command);
	}
	
	public static void testReplySQL() {
		testStoreCommand();
		IReply r = new Reply(1, "This is a reply");
		db.storeCommandReply(r);
	}
	
	public static void testStoreSensors() {
		SensorsData sensors = new SensorsData();
		
		Odometer o = new Odometer();
		o.setVal(new GaugeValueInt(3));
		sensors.addGauge(o);
		
		Fuelometer f = new Fuelometer();
		f.setVal(new GaugeValueDouble(12.3));
		sensors.addGauge(f);
		
		Speedometer s = new Speedometer();
		s.setVal(new GaugeValueInt(130));
		sensors.addGauge(s);
				
		db.storeSensorsData(sensors);
		
	}
	
	public static void testStorePicturePackage() {
		// create File
		String name = "file name";
		String data = "aGVsbG8gd29ybGQK"; // $ echo "hello world"| base64 
		long time = 1379080909;
		File f = new File();
		f.setName(name);
		f.setData(data);
		f.setCreationTime(time);
		
		// Create sensors data
		SensorsData s = new SensorsData();
		Odometer o = new Odometer();
		o.setVal(new GaugeValueInt(3));
		s.addGauge(o);
		Fuelometer fu = new Fuelometer();
		fu.setVal(new GaugeValueDouble(12.3));
		s.addGauge(fu);
		Speedometer sp = new Speedometer();
		sp.setVal(new GaugeValueInt(130));
		s.addGauge(sp);
		
		
		// Create picture package
		PicturePackage pack = new PicturePackage(s, f);
		db.storePicturePackage(pack);
	}
	

}
