package org.i2l.exdbinj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Extractor {
	protected String url = null, dbName = null, userName = null,
			password = null;
	public Connection connect = null;
	protected Statement statement = null;
	protected ResultSet resultSet = null;

	/**
	 * Constructor
	 * @param driver
	 * @param e_url
	 * @param e_db
	 * @param e_user
	 * @param e_passwd
	 */
	public Extractor(String driver, String e_url, String e_db, String e_user, String e_passwd) {
		url = driver + e_url + "/";
		dbName = e_db;
		userName = e_user;
		password = e_passwd;
		System.out.println("init mysql");
	}
	
	/**
	 * The main method, return all the results
	 * @param request: the request send to the DB
	 * @return String the results
	 */
	public abstract String getResult(String request);
	
	/**
	 * Close the connection to the DB
	 */
	protected void close() {
		try {
			if (resultSet != null) { resultSet.close(); }
			if (statement != null) { statement.close(); }
			if (connect != null) { connect.close(); }
		}
		catch (Exception e) { }
	}
}
