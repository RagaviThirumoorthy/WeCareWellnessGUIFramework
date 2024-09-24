package com.wecare.generic.databaseUtility;

/**
 * @author RAGAVI
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	
	Connection conn;
	
	public Connection getDBConnection(String url, String username, String password) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	public Connection getDBConnection() throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "KavyaDB@2712");
		return conn;
	}
	
	public void closeDBConnection() throws SQLException {
		conn.close();
	}
	
	public ResultSet executeSelectQuery(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}
	
	public int executeNonSelectQuery(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		int result = statement.executeUpdate(sql);
		return result;
	}

}
