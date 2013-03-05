package org.i2l.exdbinj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;

public abstract class Extractor {
	protected String url = null, dbName = null, userName = null,
			password = null;
	protected Connection connect = null;
	protected Statement statement = null;
	protected ResultSet resultSet = null;
	protected Document document = null;

	/**
	 * Constructor
	 * 
	 * @param driver
	 * @param e_url
	 * @param e_db
	 * @param e_user
	 * @param e_passwd
	 */
	public Extractor(String driver, String e_url, String e_db, String e_user,
			String e_passwd) {
		url = driver + e_url + "/";
		dbName = e_db;
		userName = e_user;
		password = e_passwd;
		System.out.println("init mysql");
	}

	/**
	 * The main method, return all the results
	 * 
	 * @param request
	 *            : the request send to the DB
	 * @return String the results
	 */
	public Document getResult(String request) {
		try {
			readDataBase(request);
			writeResultSet();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	protected abstract void readDataBase(String request);

	protected abstract void writeDataBase(Vector<String> vector);
	
	protected void writeResultSet() throws Exception {
		if (resultSet != null) {
			Element racine = new Element(resultSet.getMetaData()
					.getCatalogName(1));
			document = new Document(racine);

			while (resultSet.next()) {
				Element elm = new Element(resultSet.getMetaData().getTableName(
						1));
				racine.addContent(elm);

				for (int i = 1; i < resultSet.getMetaData().getColumnCount(); i++) {
					Element tmp = new Element(resultSet.getMetaData()
							.getColumnName(i));
					tmp.setText(resultSet.getString(i));
					elm.addContent(tmp);
				}
			}
		}
	}

	/**
	 * Close the connection to the DB
	 */
	protected void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
		}
	}
}