package it.unibo.droneMission.systems.headquarter.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import it.unibo.droneMission.interfaces.headquarter.IStorage;
import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.INotify;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.ISensorsData;
import it.unibo.droneMission.messages.Utils;

public abstract class Storage implements IStorage {

	public String FILE_PATH = "/media/dronemission/pictures/headquarter/missions";
	
	protected int DEBUG = -1;

	@Override
	public void setDebug(int level) {
		DEBUG = level;
	}
	
	@Override
	public void debug(String s, int level) {
		if(level <= DEBUG) {
			System.err.println(String.format("debug (%d): %s", level, s));
		}
	}
	
	@Override
	public INotify getLatestNotify() {
		List<INotify> list = getLatestNotifies(1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Override
	public ISensorsData getLatestSensorsData() {
		List<ISensorsData> list = getLatestSensorsDatas(1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public IPicturePackage getLatestPicturePackage() {
		List<IPicturePackage> list = getLatestPicturePackages(1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public String storeFile(IFile file) {
		int mission_id = getCurrentMissionID();
		String base64 = file.getDataAsBase64();
		
		String path = String.format("%s/%s", FILE_PATH, mission_id); 
		String filename = String.format("%s/%s", path, file.getName());

		// create dir
		new File(path).mkdirs();
		// get file content 
		byte[] content = Utils.decodeFileFromBase64(base64);
		
		try {
			java.io.File tmp = new java.io.File(filename);
			tmp.createNewFile();
			FileOutputStream out = new FileOutputStream(tmp);
			out.write(content);
			out.close();
			return filename;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	
}
