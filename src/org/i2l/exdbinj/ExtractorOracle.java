package org.i2l.exdbinj;

import java.sql.DriverManager;
import java.util.Vector;

public class ExtractorOracle extends Extractor {

	public ExtractorOracle(String e_url, String e_db, String e_user,
			String e_passwd) {
		super("jdbc:oracle:thin:@//", e_url, e_db, e_user, e_passwd);
	}

	protected void readDataBase(String request) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connect = DriverManager.getConnection(url + dbName, userName,
					password);

			statement = connect.createStatement();
			resultSet = statement.executeQuery(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void writeDataBase(Vector<String> request) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connect = DriverManager.getConnection(url + dbName, userName,
					password);

			statement = connect.createStatement();

			for (String s : request)
				statement.execute(s);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}