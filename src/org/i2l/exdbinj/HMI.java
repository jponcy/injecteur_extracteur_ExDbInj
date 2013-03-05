package org.i2l.exdbinj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import net.miginfocom.swing.MigLayout;

public class HMI extends JFrame {

	private static final String TITLE = "Injector/Extractor";

	public static final String[] compatibleSGBD = { "MySQL", "Oracle" };

	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField tfUrl;
	private JTextField tfRequest;
	private JTextField tfDbName;
	private JTextField tfPassword;
	private JTextField tfUser;
	private JTextField tfPathFile;
	private JComboBox choiceSGBD = new JComboBox(compatibleSGBD);

	private JButton btnImport;

	private JButton btnExport;

	private Extractor extractor;

	public HMI() {
		buildHMI();
		buildActionsListeners();

		frame.setVisible(true);
	}

	/**
	 * Initialize and place fields
	 */
	private void buildHMI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new MigLayout("", "[][][][][100px:n,grow]",
						"[][][][][][][][][][][]"));

		JLabel lblTitle = new JLabel(TITLE);
		frame.getContentPane().add(lblTitle, "cell 3 0 2 2");

		JLabel lblTypeSGBD = new JLabel("Type of SGBD");
		frame.getContentPane().add(lblTypeSGBD, "cell 3 2,alignx trailing");

		frame.getContentPane().add(choiceSGBD, "cell 4 2");

		JLabel lblUrl = new JLabel("URL");
		frame.getContentPane().add(lblUrl, "cell 3 3,alignx trailing");

		tfUrl = new JTextField();
		frame.getContentPane().add(tfUrl, "cell 4 3");
		tfUrl.setColumns(10);

		JLabel lblRequest = new JLabel("Request");
		frame.getContentPane().add(lblRequest, "cell 3 4,alignx trailing");

		tfRequest = new JTextField();
		frame.getContentPane().add(tfRequest, "cell 4 4");
		tfRequest.setColumns(10);

		JLabel lblDbName = new JLabel("DB name");
		frame.getContentPane().add(lblDbName, "cell 3 5,alignx trailing");

		tfDbName = new JTextField();
		frame.getContentPane().add(tfDbName, "cell 4 5");
		tfDbName.setColumns(10);

		JLabel lblUser = new JLabel("User");
		frame.getContentPane().add(lblUser, "cell 3 6,alignx trailing");

		tfUser = new JTextField();
		frame.getContentPane().add(tfUser, "cell 4 6");
		tfUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		frame.getContentPane().add(lblPassword, "cell 3 7,alignx trailing");

		tfPassword = new JTextField();
		frame.getContentPane().add(tfPassword, "cell 4 7");
		tfPassword.setColumns(10);

		JLabel lblPathFile = new JLabel("Path file");
		frame.getContentPane().add(lblPathFile, "cell 3 8,alignx trailing");

		tfPathFile = new JTextField();
		frame.getContentPane().add(tfPathFile, "cell 4 8 2 1");
		tfPathFile.setColumns(10);

		btnImport = new JButton("Import");
		frame.getContentPane().add(btnImport, "cell 3 10");

		btnExport = new JButton("Export");
		frame.getContentPane().add(btnExport, "cell 4 10");
	}

	private void buildActionsListeners() {
		btnImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFromDB();
			}
		});
		btnExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exportToDB();
			}
		});
	}

	protected void exportToDB() {
		System.out.println("Launch an exportation");
		

		if (compatibleSGBD[choiceSGBD.getSelectedIndex()] == "MySQL") {
			extractor = new ExtractorMySql(tfUrl.getText(), tfDbName.getText(),
					tfUser.getText(), tfPassword.getText());
		}

		if (compatibleSGBD[choiceSGBD.getSelectedIndex()] == "Oracle") {
			extractor = new ExtractorOracle(tfUrl.getText(),
					tfDbName.getText(), tfUser.getText(), tfPassword.getText());
		}
		
		extractor.writeDataBase(XMLConverter.convertXmlToScript(new File(tfPathFile.getText())));
	}

	protected void importFromDB() {
		System.out.println("Launch an importation");

		if (compatibleSGBD[choiceSGBD.getSelectedIndex()] == "MySQL") {
			extractor = new ExtractorMySql(tfUrl.getText(), tfDbName.getText(),
					tfUser.getText(), tfPassword.getText());
		}

		if (compatibleSGBD[choiceSGBD.getSelectedIndex()] == "Oracle") {
			extractor = new ExtractorOracle(tfUrl.getText(),
					tfDbName.getText(), tfUser.getText(), tfPassword.getText());
		}

		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(extractor.getResult(tfRequest.getText()),
					new FileOutputStream(tfPathFile.getText()));
		} catch (java.io.IOException e) {
		}
	}


}