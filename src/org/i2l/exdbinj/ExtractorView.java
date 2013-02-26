package org.i2l.exdbinj;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExtractorView extends JFrame {
	private static final int SIZE_TF = 50;
	private static final long serialVersionUID = 1L;
	private JTextField url;
	private JTextField typeSGBD;
	private JTextField host;
	private JTextField port;
	private JTextField dbName;
	private JTextField user;
	private JTextField password;
	private JTextArea request;

	public ExtractorView() {
		buildUI();
		setSize(800, 600);
		setVisible(true);
	}

	private void buildUI() {
		this.setTitle("Extractor");
	    this.setSize(300, 120);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	 
	    //type of sgbd
	    Box typeSgbdBox = Box.createHorizontalBox();
	    typeSgbdBox.add(new JLabel("Type dy SGBD: "));
	    typeSGBD = new JTextField(SIZE_TF); // TODO mettre une combo
	    typeSGBD.setSize(new Dimension(WIDTH, 1));
	    typeSgbdBox.add(typeSGBD);
	    //url
	    Box urlBox = Box.createHorizontalBox();
	    urlBox.add(new JLabel("URL: "));
	    url = new JTextField(SIZE_TF);
	    urlBox.add(url);
	    //host
	    Box hostBox = Box.createHorizontalBox();
	    hostBox.add(new JLabel("Host: "));
	    host = new JTextField(SIZE_TF);
	    hostBox.add(host);
	    //port
	    Box portBox = Box.createHorizontalBox();
	    portBox.add(new JLabel("Port: "));
	    port = new JTextField(SIZE_TF);
	    portBox.add(port);
	    //db name
	    Box dbNameBox = Box.createHorizontalBox();
	    dbNameBox.add(new JLabel("DB name: "));
	    dbName = new JTextField(SIZE_TF);
	    dbNameBox.add(dbName);
	    // user
	    Box userBox = Box.createHorizontalBox();
	    userBox.add(new JLabel("User: "));
	    user = new JTextField(SIZE_TF);
	    userBox.add(user);
	    // password
	    Box passwordBox = Box.createHorizontalBox();
	    passwordBox.add(new JLabel("Password: "));
	    password = new JTextField(SIZE_TF);
	    passwordBox.add(password);
	    // requête
	    Box requestBox = Box.createHorizontalBox();
	    requestBox.add(new JLabel("Request: "));
	    request = new JTextArea(3, SIZE_TF);
	    requestBox.add(request);

	    //ajouts
	    Box fields = Box.createVerticalBox();
	    fields.add(typeSgbdBox);
	    fields.add(urlBox);
	    fields.add(hostBox);
	    fields.add(portBox);
	    fields.add(dbNameBox);
	    fields.add(userBox);
	    fields.add(passwordBox);
	    fields.add(requestBox);
/*
    -> type SGBD (oracle, postgresql, mysql, orbc, ...)

    -> URL (sous la forme: jdbc:<type_sgbd>:\\<host>:<num_port>\<db_name> - à générer automatiquement en fonction des champs ci-dessous)
        -> HOST:
        -> PORT:
        -> DB name:
        -> USER:
        -> PASSWORD:

    -> Requête	 
 */
	    this.getContentPane().add(fields);
	    this.setVisible(true);
	}
}
