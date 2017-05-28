package com.userkanri.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnect {
	public static Connection getConnection()
			throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
	    String dbName = "fels_db";
	    String userName = "root";
	    String password = "";
	    return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySQLConnection(String hostName, String dbName,
	        String userName, String password) throws SQLException,
	        ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL = "jdbc:mysql://localhost:3306/fels_db";
		 
	    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	    return conn;
	}

}
