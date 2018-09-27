package ammar1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class user1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user1 frame = new user1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public user1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(69, 59, 46, 14);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(179, 56, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setBounds(69, 109, 46, 14);
		contentPane.add(lblPrice);
		
		textField_1 = new JTextField();
		textField_1.setBounds(179, 106, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(69, 158, 46, 14);
		contentPane.add(lblId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(179, 155, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAmount = new JLabel("amount");
		lblAmount.setBounds(69, 204, 46, 14);
		contentPane.add(lblAmount);
		
		textField_3 = new JTextField();
		textField_3.setBounds(179, 201, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection myConn = null;
				PreparedStatement myStmt = null;
				//ResultSet myRes = null;
				String dburl = "jdbc:mysql://localhost:3306/ammar";
				String user = "root";
				String pass = "";
				
				try {
					myConn = DriverManager.getConnection(dburl, user, pass);
					myStmt = myConn.prepareStatement("INSERT INTO products(name,price,id,amout) VALUES (?,?,?,?) ");
					myStmt.setString(1, textField.getText());
					myStmt.setString(2, textField_1.getText());
					myStmt.setString(3, textField_2.getText());
					myStmt.setString(4, textField_3.getText());
					myStmt.execute();
					JOptionPane.showMessageDialog(null, "added succeffuly");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
				}
				catch (Exception e) {
					System.out.println("moro");
				}
			}
		});
		btnAdd.setBounds(321, 122, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				first1 f = new first1();
				f.setVisible(true);
			}
		});
		btnBack.setBounds(321, 168, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnResetDatabase = new JButton("Reset DataBase");
		btnResetDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection myConn = null;
				PreparedStatement myStmt = null;
				//ResultSet myRes = null;
				String dburl = "jdbc:mysql://localhost:3306/ammar";
				String user = "root";
				String pass = "";
				
				try {
					myConn = DriverManager.getConnection(dburl, user, pass);
					myStmt = myConn.prepareStatement("DELETE FROM products WHERE true");
					myStmt.execute();
					JOptionPane.showMessageDialog(null, "Reset succeffuly");
			}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnResetDatabase.setBounds(291, 212, 119, 23);
		contentPane.add(btnResetDatabase);
		
	}
}
