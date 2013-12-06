package it.unibo.droneMission.messages;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.unibo.droneMission.interfaces.messages.IFile;
import it.unibo.droneMission.interfaces.messages.IPicturePackage;
import it.unibo.droneMission.interfaces.messages.ISensorsData;

public class PicturePackage implements IPicturePackage {

	protected IFile file;
	protected ISensorsData sensors;
	
	public PicturePackage(SensorsData sensors, File file) {
		this.file = file;
		this.sensors = sensors;
	}
	
	@Override
	public void setFile(IFile file) {
		this.file = file;	
	}

	@Override
	public IFile getFile() {
		return file;
	}

	@Override
	public void setSensorsData(ISensorsData sensors) {
		this.sensors = sensors;		
	}

	@Override
	public ISensorsData getSensorsData() {
		return sensors;
	}
	
	@Override
	public String toJSON() {
		JsonObject result = new JsonObject();
		JsonParser parser = new JsonParser();
		// add sensorsData
		JsonElement tmp = parser.parse(sensors.toJSON());
		result.add("sensorsData", tmp);
		// add file
		tmp = parser.parse(file.toJSON());
		result.add("file", tmp);
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}	

}
