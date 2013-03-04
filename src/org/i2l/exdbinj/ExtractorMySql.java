package org.i2l.exdbinj;

import java.sql.DriverManager;

import org.jdom.Document;
import org.jdom.Element;

public class ExtractorMySql extends Extractor{
	
	private Document document;
	
	public ExtractorMySql(String e_url, String e_db, String e_user, String e_passwd){
		super("jdbc:mysql://", e_url, e_db, e_user, e_passwd);
	}
	
	public Document getResult(String request){
		try {
			readDataBase(request);
			writeResultSet();
			close();
		}
		catch (Exception e) { e.printStackTrace(); }
		return document;
	}

	private void writeResultSet() throws Exception {
		if (resultSet != null) {
			Element racine = new Element(resultSet.getMetaData().getCatalogName(1));
			document = new Document(racine);
			
			while (resultSet.next()) {
				Element elm = new Element(resultSet.getMetaData().getTableName(1));
				racine.addContent(elm);

				for (int i = 1; i < resultSet.getMetaData().getColumnCount(); i++) {
					Element tmp = new Element(resultSet.getMetaData().getColumnName(i));
					tmp.setText(resultSet.getString(i));
					elm.addContent(tmp);
				}
			}
		}
	}
	
	private void readDataBase(String request) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url + dbName, userName, password);
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery(request);
		}
		catch (Exception e) { throw e; }
	}
}