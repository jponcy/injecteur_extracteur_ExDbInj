package org.i2l.exdbinj;

import java.sql.DriverManager;

public class ExtractorMySql extends Extractor{
	public ExtractorMySql(String e_url, String e_db, String e_user, String e_passwd){
		super("jdbc:mysql://", e_url, e_db, e_user, e_passwd);
	}
	
	public String getResult(String request){
		try {
			readDataBase(request);
			return writeResultSet();
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("toto"); }
		return null;
	}
	
	
	private String writeResultSet() throws Exception {
		String tmp = null;
		while (resultSet.next()) {
			
			int nbColumn = resultSet.getMetaData().getColumnCount();
			
			for(int i=0 ; i < nbColumn ; i++){
				//tmp += resultSet.getString(i);
				tmp += resultSet.getString(i);
				tmp += " : ";
			}
			
			tmp += " / ";
			/*
			String id = resultSet.getString("idgroupe");
			String name = resultSet.getString("nomgroupe");
			String comment = resultSet.getString("commentairegroupe");
			tmp += "id : " + id + " / group name : " + name + " / commentaire : " + comment;
			System.out.println("id : " + id + " / group name : " + name + " / commentaire : " + comment);
			*/
		}
		return tmp;
	}
	
	

	private void readDataBase(String request) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("getConnect");
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery(request);
			System.out.println("good result");
		}
		catch (Exception e) { throw e; }
		//finally { close(); }
	}
	
	
	/*
	ResultSet rs = statement.executeQuery(req);

	if (rs != null) {

		// root elements
		Element rootElement = doc.createElement(dbName);
		doc.appendChild(rootElement);

		while (rs.next()) {

			Element table = doc.createElement("Etudiant");
			rootElement.appendChild(table);

			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				Element node = doc.createElement(rs.getMetaData().getColumnName(i));
				node.appendChild(doc.createTextNode(rs.getString(i)));
				table.appendChild(node);
			}

		}

		transformer.transform(source, result);

		System.out.println("File saved!");

	}
	*/
}