package it.unibo.droneMission.systems.headquarter;

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
	
	public static MySQL getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null)
			instance = new MySQL();
		return instance;
	}
	

	@Override
	public void connect() {
		try {
			String url = String.format("jdbc:mysql://%s:%d/%s", host, port, dbname);
			db =  DriverManager.getConnection(url, username, password);
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
	public int update(Hashtable<String, String> set) {
		
		String updateValues = joinSet(set, ", "); 
		String table = joinList(from, ", ", false);
		String where = joinSet(this.where, " AND ");
				
		String sql = String.format("UPDATE %s SET %s", table, updateValues);

		if(where.length() > 0)
				sql += " WHERE " + where;
		
		resetSQLParams();
		
		try {
			debug(sql, 3);
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("Error in executing udpate().");
			System.err.println("SQL:\n"+sql);
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int insert(Hashtable<String, String> set) {
		String table = joinList(from, ", ", false);
		
		// get to ordered arrays
		ArrayList<String> keys = new ArrayList<String>(set.keySet());
		ArrayList<String> vals = new ArrayList<String>();
		
		for(int i = 0; i < keys.size() ; i++)
			vals.add(set.get(keys.get(i)));
				
		String columns = joinList(keys, ", ", false);
		String values = joinList(vals, ", ");
		
		String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", table, columns, values);

		resetSQLParams();
	
		try {
			debug(sql, 3);
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("Error in executing insert().");
			System.err.println("SQL:\n"+sql);
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public ResultSet get() {
		if (this.select.size() == 0)
			this.select.add("*");
		
		String where = joinSet(this.where, " AND ");
		String from = joinList(this.from, ", ", false);
		String select = joinList(this.select, ", ", false);
		
		String sql = String.format("SELECT %s FROM %s", select, from);
		
		if (where.length() > 0)
			sql += " WHERE " + where;
		
		if (orderBy.length() > 0) {
			sql += String.format(" ORDER BY %s %s", orderBy, orderByDirection); 
		}
		
		if (limit >= 0)
			sql += " LIMIT " + limit;

		if (offset >= 0)
			sql += " OFFSET " + offset;
		
		resetSQLParams();
		
		try {
			debug(sql, 3);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("Error in executing get().");
			System.err.println("SQL:\n"+sql);
			e.printStackTrace();
		}
		return null;
	}
	
	private String joinSet(Hashtable<String, String> set, String separator) {
		String result = "";
		Iterator<Map.Entry<String, String>> it = set.entrySet().iterator();
		while (it.hasNext()) {
			  Map.Entry<String, String> entry = it.next();
			  result += String.format("%s = '%s'", entry.getKey(), entry.getValue());
			  if (it.hasNext()) result += separator;
		}
		return result;
	}
	
	private String joinList(ArrayList<String> list, String separator) {
		return joinList(list, separator, true);
	}
	
	private String joinList(ArrayList<String> list, String separator, boolean withQuotes) {
		String result = "";
		Iterator<String> it = list.iterator();
		while(it.hasNext())
		{
			if (withQuotes)
				result  += String.format("'%s'", it.next());
			else
				result  += String.format("%s", it.next());
		    if (it.hasNext()) result += separator;
		}
		return result;
	}
	
}
