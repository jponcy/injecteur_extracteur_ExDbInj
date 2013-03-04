package org.i2l.exdbinj;

import java.io.FileOutputStream;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Main {
	public static void main(String[] args) {
		new ExtractorView();

		// affiche les requêtes SQL depuis un XML
		//System.out.println(XMLConverter.convertXmlToScript(new File("/home/zender/Bureau/BDA/injecteur_extracteur_ExDbInj/tests_files/test.xml")));

		ExtractorMySql em = new ExtractorMySql("localhost", "bda", "root", "toor");

		System.out.println(em.getResult("select * from groupes;").toString());
		
		// affichage
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(em.getResult("select * from groupes;"), System.out);
		}
		catch (java.io.IOException e){}

		// sauvegarde à la racine du projet
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output( em.getResult("select * from groupes;"), new FileOutputStream("tatat.xml"));
		}
		catch (java.io.IOException e){}

	}
}
