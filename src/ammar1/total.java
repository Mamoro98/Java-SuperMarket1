package ammar1;
import net.proteanit.sql.DbUtils;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class total extends JFrame {

	private JPanel contentPane;
	private JTable JTable;
	private JLabel label;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					total frame = new total();
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
	public total() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTable = new JTable();
		JTable.setBounds(10, 11, 414, 156);
		contentPane.add(JTable);
		Connection myConn = null;
		PreparedStatement sumstat = null ;
		PreparedStatement myStmt = null;
		PreparedStatement dele = null ;
		ResultSet myRes = null;
		ResultSet sumres = null ;
		String dburl = "jdbc:mysql://localhost:3306/ammar";
		String user = "root";
		String pass = "";
		try {
			myConn = DriverManager.getConnection(dburl, user, pass);
			myStmt = myConn.prepareStatement("SELECT b.amount , p.name from bought b , products p where p.id = b.id");
		    myRes = myStmt.executeQuery("SELECT b.amount , p.name from bought b , products p where p.id = b.id");
			JTable.setModel(DbUtils.resultSetToTableModel(myRes));
			
			JLabel lblTotalCostIs = new JLabel("total cost is ");
			lblTotalCostIs.setBounds(36, 201, 128, 14);
			contentPane.add(lblTotalCostIs);
			
			label = new JLabel("");
			label.setBounds(220, 201, 46, 14);
			contentPane.add(label);
			
			btnBack = new JButton("back");
			btnBack.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					client1 c = new client1();
					c.setVisible(true);
				}
			});
			btnBack.setBounds(301, 227, 89, 23);
			contentPane.add(btnBack);
			
			
			sumstat = myConn.prepareStatement("SELECT sum(p.price) from products p , bought b where p.id = b.id ");
			sumres = sumstat.executeQuery("SELECT sum(p.price) val from products p , bought b where p.id = b.id ");
			while(sumres.next()) {
				label.setText(sumres.getString("val"));
			}
			dele = myConn.prepareStatement("DELETE FROM bought WHERE 1");
			dele.execute();
		  }
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
