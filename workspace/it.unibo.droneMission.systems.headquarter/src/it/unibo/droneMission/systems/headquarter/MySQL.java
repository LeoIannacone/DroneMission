package it.unibo.droneMission.systems.headquarter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

import it.unibo.contact.DroneMissionSystem.DataBase;

public class MySQL extends DataBase {

	private static MySQL instance;
	
	protected ArrayList<String> select;
	protected ArrayList<String> from;
	protected Hashtable<String, String> where;
	protected String orderBy;
	protected String orderByDirection;
	protected int limit;
	protected int offset;
	
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
	public void select(String column) {
		this.select.add(column);
	}

	@Override
	public void select(String[] columns) {
		for (String c : columns)
			this.select(c);
	}

	@Override
	public void from(String table) {
		this.from.add(table);
	}

	@Override
	public void from(String[] tables) {
		for (String t : tables)
			this.from(t);
	}

	@Override
	public void where(String key, String value) {
		this.where.put(key, value);
	}

	@Override
	public void where(Hashtable<String, String> set) {
		this.where.putAll(set);
		
	}

	@Override
	public void orderBy(String column, String direction) {
		this.orderBy = column;
		this.orderByDirection = direction;
	}

	@Override
	public void limit(int n) {
		this.limit = n;
	}

	@Override
	public void offset(int n) {
		this.offset = n;
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
