package ammar1;
// moro
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.sql.DataSource; 
public class client1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client1 frame = new client1();
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
	public client1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToEee = new JLabel("Welcome to EEE015 Supermaket ");
		lblWelcomeToEee.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToEee.setBounds(96, 0, 236, 50);
		contentPane.add(lblWelcomeToEee);
		
		JLabel lblPleaseCheckThe = new JLabel("Please Check the id of what you need");
		lblPleaseCheckThe.setBounds(128, 46, 204, 14);
		contentPane.add(lblPleaseCheckThe);
		
		JLabel lblProductId = new JLabel("product id");
		lblProductId.setBounds(79, 113, 99, 14);
		contentPane.add(lblProductId);
		
		textField = new JTextField();
		textField.setBounds(233, 110, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(358, 110, 29, 20);
		contentPane.add(spinner);
		
		
		JButton btnBuy = new JButton("buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection myConn = null;
				PreparedStatement myStmt = null;
				//ResultSet myRes = null;
				String dburl = "jdbc:mysql://localhost:3306/ammar";
				String user = "root";
				String pass = "";
				
				try {
					myConn = DriverManager.getConnection(dburl, user, pass);
					myStmt = myConn.prepareStatement("INSERT INTO bought(id,amount) VALUES (?,?) ");
					//myRes = myStmt.executeQuery();
					myStmt.setString(1, textField.getText());
					
					myStmt.setString(2,spinner.getValue().toString() );
					
					myStmt.execute();
					textField.setText("");
				} catch (Exception exception) {
                System.out.println("moro");		
                          }
				
			}
		});
		btnBuy.setBounds(233, 152, 89, 23);
		contentPane.add(btnBuy);
		
		JButton btnTotal = new JButton("total");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				total t = new total();
				t.setVisible(true);
			}
		});
		btnTotal.setBounds(79, 200, 89, 23);
		contentPane.add(btnTotal);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			first1 f = new first1();
			f.setVisible(true);
			}
			
		});
		btnBack.setBounds(233, 200, 89, 23);
		contentPane.add(btnBack);
	}
}
