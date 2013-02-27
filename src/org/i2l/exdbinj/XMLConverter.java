package org.i2l.exdbinj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;
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

	public static void main(String[] args) {
		XMLConverter xmlConverter = new XMLConverter(
				new File(
						"/home/jponcy/Documents/cours/base_de_donnees_reparti/injecteur_extracteur_ExDbInj/tests_files/test.xml"));
		xmlConverter.readXML();
		System.out.println(xmlConverter);
	}

	public String toString() {
		String toReturn = "Print data";
		for(Table t:data) {
			toReturn += "\n" + t;
		}
		return toReturn;
	}

	// TODO Look about params of the next functions
	private void generateRequestOfCreationOfTable() {
		// TODO Auto-generated method stub
	}

	private void generateRequestOfAddToOneTable() {
		// TODO Auto-generated method stub
	}

	private Vector<String> generateScriptOfCreationAndFillDatabase() {
		Map<String, String> scriptsOfCreation = null; // 1: nom bdd, 2: script
		Vector<String> scriptsOfFill = null;
		// TODO Auto-generated method stub
		// for all tables
		generateRequestOfCreationOfTable();
		// for all line (of value)
		generateRequestOfAddToOneTable();

		return null;
	}

	public Vector<String> getScripts() {
		return generateScriptOfCreationAndFillDatabase();
	}

	public static Vector<String> convertXmlToScript(File file) {
		return new XMLConverter(file).getScripts();
	}
}