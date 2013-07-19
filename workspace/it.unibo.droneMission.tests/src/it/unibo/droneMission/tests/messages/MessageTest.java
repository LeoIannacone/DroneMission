package it.unibo.droneMission.tests.messages;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.unibo.droneMission.interfaces.messages.TypesCommand;
import it.unibo.droneMission.prototypes.messages.Command;
import it.unibo.droneMission.prototypes.messages.Factory;
import it.unibo.droneMission.prototypes.messages.Message;
public class MessageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Command c = new Command(TypesCommand.START_MISSION, 4);
//		c.setValue(3);
		Gson gson = new Gson();
		System.out.println(c);
		String json = c.toJSON();
		System.out.println(json);
		
		Command c1 = (Command) Factory.createCommand(json);
		
		System.out.println(c1);
		
	}

}
