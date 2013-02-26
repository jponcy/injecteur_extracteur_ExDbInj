package org.i2l.exdbinj;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XMLConverter {
	Files originFile = null;
	Element racine = new Element("personnes");

	//On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	org.jdom.Document document = new Document(racine);

	public XMLConverter(Files myXmlFile) {
		originFile = myXmlFile;
	}

	@SuppressWarnings("unused")
	private void readXML() {
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			//Le parsing est terminé ;)
			document = sxb.build(new File("Exercice2.xml"));
		}
		catch(Exception e){}

		//On initialise un nouvel élément racine avec l'élément racine du document.
		racine = document.getRootElement();

		//Méthode définie dans la partie 3.2. de cet article
		afficheALL();
	}
	void afficheALL()
	{
		//On crée une List contenant tous les noeuds "etudiant" de l'Element racine
		@SuppressWarnings("unchecked")
		List<Element> listEtudiants = racine.getChildren("etudiant");

		//On crée un Iterator sur notre liste
		Iterator<Element> i = listEtudiants.iterator();
		while(i.hasNext())
		{
			//On recrée l'Element courant à chaque tour de boucle afin de
			//pouvoir utiliser les méthodes propres aux Element comme :
			//sélectionner un nœud fils, modifier du texte, etc...
			Element courant = (Element)i.next();
			//On affiche le nom de l’élément courant
			System.out.println(courant.getChild("nom").getText());
		}
	}

	/**
	 * Print the XML
	 */
	public void print() {
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){}
	}
}