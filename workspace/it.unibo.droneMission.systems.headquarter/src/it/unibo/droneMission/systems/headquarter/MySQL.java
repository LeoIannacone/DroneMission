package it.unibo.droneMission.systems.headquarter;

import java.security.KeyPair;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;


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
		
		String updateValues = joinSet(set, ", "); 
		String table = joinList(from, ", ");
		String where = joinSet(this.where, " AND ");
				
		String sql = String.format("UPDATE %s SET %s", table, updateValues);

		if(where.length() > 0)
				sql += where;
		
		return executeQuery(sql);
	}

	@Override
	public ResultSet insert(Hashtable<String, String> set) {
		String values = joinSet(set, ", ");
		String table = joinList(from, ", ");
		
		String sql = String.format("INSERT IN %s (%s)");
		return executeQuery(sql);
		
	}

	@Override
	public ResultSet get() {
		if (this.select.size() == 0)
			this.select.add("*");
		
		String where = joinSet(this.where, " AND ");
		String from = joinList(this.from, ", ");
		String select = joinList(this.select, ", ");
		
		String sql = String.format("SELECT %s FROM %s", select, from);
		
		if (where.length() > 0)
			sql += " WHERE " + where;
		
		if (limit >= 0)
			sql += " LIMIT " + limit;
		if (orderBy.length() > 0) {
			sql += String.format(" ORDER BY %s %s", orderBy, orderByDirection); 
		}
		if (offset >= 0)
			sql += " OFFSET " + offset;
		
		return executeQuery(sql);
	}
	
	private String joinSet(Hashtable<String, String> set, String separator) {
		String result = "";
		Iterator<Map.Entry<String, String>> it = set.entrySet().iterator();
		while (it.hasNext()) {
			  Map.Entry<String, String> entry = it.next();
			  result += String.format("'%s' = '%s'", entry.getKey(), entry.getValue());
			  if (it.hasNext()) result += separator;
		}
		return result;
	}
	
	private String joinList(ArrayList<String> list, String separator) {
		String result = "";
		Iterator<String> it = list.iterator();
		while(it.hasNext())
		{
		    result  += String.format("'%s'", it.next());
		    if (it.hasNext()) result += separator;
		}
		return result;
	}
	
}
