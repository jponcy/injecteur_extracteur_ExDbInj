package org.i2l.exdbinj;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XMLConverter {
	private File originFile = null;
	private Element racine = new Element("personnes");

	private Vector<Table> data = new Vector<Table>();

	// On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	org.jdom.Document document = new Document(racine);

	public XMLConverter(File myXmlFile) {
		originFile = myXmlFile;
		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try {
			// On crée un nouveau document JDOM avec en argument le fichier XML
			// Le parsing est terminé ;)
			document = sxb.build(originFile);
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unused")
	private void testFile() {
		FileInputStream fis;
		try {
			String s = "";
			fis = new FileInputStream(originFile);
			int n;
			while ((n = fis.available()) > 0) {
				byte[] b = new byte[n];
				int result = fis.read(b);
				if (result == -1)
					break;
				s = new String(b);
			}
			fis.close();
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void readXML() {
		// On initialise un nouvel élément racine avec l'élément racine du document.
		racine = document.getRootElement();
		/*
		 * niveau 1: bdd niveau 2: table niveau 3: valeur de table
		 */
		Table tableTemp;
		List<Element> children = racine.getChildren();
		for (Element child : children) { // tables nodes
			List<Element> resultat = child.getContent();
			tableTemp = new Table(child.getName());
			System.out.println("Debut exception");

			for (int i = 0; i < resultat.size(); i++) {
				if (resultat.get(i) instanceof Element) {
					Element mye = (Element) resultat.get(i);
					tableTemp.addValue(mye.getName(), mye.getValue());
				}
			}
			data.add(tableTemp);
			System.out.println("Fin exception");
		}
	}

	/**
	 * Print the XML
	 */
	public void print() {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		} catch (java.io.IOException e) {
		}
	}

	public String toString() {
		String toReturn = "Print data";
		for(Table t:data) {
			toReturn += "\n" + t;
		}
		return toReturn;
	}

	/**
	 * Generate the queries to create the db
	 * @return
	 */
	private Map<String, String> generateRequestOfCreationOfTable() {
		Map<String, String> toReturn = new HashMap<String, String>();
		String request;
		for(Table table:data) {
			// we test if we know already this table
			if(!toReturn.containsKey(table.getName())) {
				request = String.format("CREATE TABLE %s IF NOT EXIST(", table.getName());
				Map<String, String> values = table.getValues();
				for( Iterator<String> ii = values.keySet().iterator(); ii.hasNext();) {
					String columnName = (String)ii.next();
					request += columnName + " VARCHAR(150)";
					if(ii.hasNext())
						request+=",";
				}
				request += ")";
				toReturn.put(table.getName(), request);
			}
		}
		return toReturn;
	}

	private Vector<String> generateRequestOfAddToOneTable() {
		Vector<String> toReturn = new Vector<String>();
		final String genericRequest = "INSERT INTO %s (%s)VALUES (%s)";
		String tables;
		String values;
		for(Table table:data) {
			// init vars
			tables = "";
			values = "";
			// get the goods attributes
			Map<String, String> valuesMap = table.getValues();
			for( Iterator<String> ii = valuesMap.keySet().iterator(); ii.hasNext();) {
				String key = (String)ii.next();;
				tables += key;
				values += "\"" + (String) valuesMap.get(key) + "\"";
				if(ii.hasNext()) {
					tables += ",";
					values += ",";
				}
			}
			// add the query
			toReturn.add(String.format(genericRequest, table.getName(), tables, values));
		}
		return toReturn;
	}

	private Vector<String> generateScriptOfCreationAndFillDatabase() {
		Map<String, String> scriptsOfCreation = null; // 1: nom bdd, 2: script
		Vector<String> scriptsOfFill = null;
//		scriptsOfCreation = generateRequestOfCreationOfTable();
		scriptsOfFill = generateRequestOfAddToOneTable();

		if(scriptsOfFill == null)
			scriptsOfFill = new Vector<String>();

		// add the creations script before inserts
//		if (scriptsOfCreation != null ) { 
//			for( Iterator<String> ii = scriptsOfCreation.keySet().iterator(); ii.hasNext();) {
//				String value = (String) scriptsOfCreation.get(ii.next());
//				scriptsOfFill.add(0, value);
//			}
//		}
		return scriptsOfFill;
	}

	public Vector<String> getScripts() {
		return generateScriptOfCreationAndFillDatabase();
	}

	public static Vector<String> convertXmlToScript(File file) {
		XMLConverter xmlConverter = new XMLConverter(file);
		xmlConverter.readXML();
		return xmlConverter.getScripts();
	}
}