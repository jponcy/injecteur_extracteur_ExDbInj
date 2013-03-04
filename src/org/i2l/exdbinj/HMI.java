package org.i2l.exdbinj;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class HMI extends JFrame {

	private static final String TITLE = "Injector/Extractor";

	public static final String[] compatibleSGBD = {"MySQL"};
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField tfUrl;
	private JTextField tfHost;
	private JTextField tfPort;
	private JTextField tfDbName;
	private JTextField tfPassword;
	private JTextField tfUser;
	private JComboBox<String> choiceSGBD = new JComboBox<String>(compatibleSGBD);

	private JButton btnImport;

	private JButton btnExport;


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
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][100px:n,grow]", "[][][][][][][][][][][]"));

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

		JLabel lblHost = new JLabel("Host");
		frame.getContentPane().add(lblHost, "cell 3 4,alignx trailing");

		tfHost = new JTextField();
		frame.getContentPane().add(tfHost, "cell 4 4");
		tfHost.setColumns(10);

		JLabel lblPort = new JLabel("Port");
		frame.getContentPane().add(lblPort, "cell 3 5,alignx trailing");

		tfPort = new JTextField();
		frame.getContentPane().add(tfPort, "cell 4 5");
		tfPort.setColumns(10);

		JLabel lblDbName = new JLabel("DB name");
		frame.getContentPane().add(lblDbName, "cell 3 6,alignx trailing");

		tfDbName = new JTextField();
		frame.getContentPane().add(tfDbName, "cell 4 6");
		tfDbName.setColumns(10);

		JLabel lblUser = new JLabel("User");
		frame.getContentPane().add(lblUser, "cell 3 7,alignx trailing");

		tfUser = new JTextField();
		frame.getContentPane().add(tfUser, "cell 4 7");
		tfUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		frame.getContentPane().add(lblPassword, "cell 3 8,alignx trailing");

		tfPassword = new JTextField();
		frame.getContentPane().add(tfPassword, "cell 4 8");
		tfPassword.setColumns(10);

		
		btnImport = new JButton("Import");
		frame.getContentPane().add(btnImport, "cell 3 9");

		btnExport = new JButton("Export");
		frame.getContentPane().add(btnExport, "cell 4 9");

		JLabel lblFilePath = new JLabel("Path file ...");
		frame.getContentPane().add(lblFilePath, "cell 3 10 2 1");
	}

	private void buildActionsListeners() {
//		request.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_ENTER)
//					send();
//			}
//		});
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
		// TODO Auto-generated method stub
		System.out.println("Launch an exportation");
	}

	protected void importFromDB() {
		// TODO Auto-generated method stub
		System.out.println("Launch an importation");
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HMI window = new HMI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}