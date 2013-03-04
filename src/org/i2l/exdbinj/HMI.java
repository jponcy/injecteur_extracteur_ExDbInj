package org.i2l.exdbinj;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class HMI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;








	public HMI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][100px:n,grow]", "[][][][][][][][][][][]"));

		JLabel lblNewLabel_5 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_5, "cell 3 0 2 2");

		JLabel lblNewLabel_6 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_6, "cell 3 2,alignx trailing");

		textField_5 = new JTextField();
		frame.getContentPane().add(textField_5, "cell 4 2");
		textField_5.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel, "cell 3 3,alignx trailing");

		textField = new JTextField();
		frame.getContentPane().add(textField, "cell 4 3");
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_1, "cell 3 4,alignx trailing");

		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1, "cell 4 4");
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_2, "cell 3 5,alignx trailing");

		textField_2 = new JTextField();
		frame.getContentPane().add(textField_2, "cell 4 5");
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_3, "cell 3 6,alignx trailing");

		textField_3 = new JTextField();
		frame.getContentPane().add(textField_3, "cell 4 6");
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4, "cell 3 7,alignx trailing");

		textField_4 = new JTextField();
		frame.getContentPane().add(textField_4, "cell 4 7");
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		frame.getContentPane().add(btnNewButton, "cell 3 8");

		JButton btnNewButton_1 = new JButton("New button");
		frame.getContentPane().add(btnNewButton_1, "cell 4 8");

		JLabel lblNewLabel_7 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_7, "cell 3 10 2 1");
		
		frame.setVisible(true);
	}



}




/*
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				fenetre window = new fenetre();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
 */