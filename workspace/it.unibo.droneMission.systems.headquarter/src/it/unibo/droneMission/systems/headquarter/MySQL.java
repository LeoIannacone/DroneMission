package it.unibo.droneMission.systems.headquarter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;


public class MySQL extends DataBase {

	private static MySQL instance;
	
	private MySQL() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		this.host = "localhost"; // default set to localhost
		this.port = 3306; // default mysql port
		resetSQLParams();
	}
	
	private void resetSQLParams() {
		select = new ArrayList<String>();
		from = new ArrayList<String>();
		where = new Hashtable<String, String>();
		orderBy = "";
		orderByDirection = "";
		limit = -1;
		offset = -1;
	}
	
	public static MySQL getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null)
			instance = new MySQL();
		return instance;
	}
	

	@Override
	public void connect() {
		try {
			db =  DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s",
					dbname,
					host,
					port,
					username,
					password)
				);
		} catch (SQLException e) {
			System.err.println("Error in connecting to database.");
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			stmt =  (Statement) db.createStatement();
		} catch (SQLException e) {
			System.err.println("Error in creating query statement.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public ResultSet update(Hashtable<String, String> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet insert(Hashtable<String, String> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet get() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
