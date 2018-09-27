package ammar1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("username ");
		lblUsername.setBounds(78, 84, 82, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(197, 81, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(197, 140, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnLogIn = new JButton("log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection myConn = null;
				PreparedStatement myStmt = null;
				ResultSet myRes = null;
				String dburl = "jdbc:mysql://localhost:3306/ammar";
				String user = "root";
				String pass = "";
				
				try {
					myConn = DriverManager.getConnection(dburl, user, pass);
					myStmt = myConn.prepareStatement("SELECT `user`,`password` FROM `admin` WHERE `user`=? AND `password`=?;");
					myStmt.setString(1,textField.getText() );
					myStmt.setString(2,String.valueOf(passwordField.getPassword()));
					myRes = myStmt.executeQuery();
					if (myRes.next() ) {
						dispose();
						user1 u = new user1();
						u.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong password / username ");
						passwordField.setText("");
						textField.setText("");
					}
			}
				catch (Exception e) {
					System.out.println("moro");
				}
			}
		});
		btnLogIn.setBounds(78, 200, 89, 23);
		contentPane.add(btnLogIn);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				first1 f = new first1();
				f.setVisible(true);
			}
		});
		btnBack.setBounds(197, 200, 89, 23);
		contentPane.add(btnBack);
		
		lblPassword = new JLabel("password");
		lblPassword.setBounds(78, 143, 82, 14);
		contentPane.add(lblPassword);
		
		
	}
}
