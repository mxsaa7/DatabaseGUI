import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.List;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.*;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.SystemColor;


public class InfoDB {

	private JFrame frame;
	private JTextField entryText;
	private JTextField emailText;
	private JTextField dateText;
	private JTextField pswdText;
	private JTable table;
	
	DefaultTableModel model;
	String url = "jdbc:mysql://localhost:3306/credentials";
	String username = "CST2355";
	String password = "CST2355Database";
	Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoDB window = new InfoDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InfoDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
				
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		Panel panel = new Panel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setForeground(Color.GRAY);
		panel.setBounds(30, 107, 508, 464);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(96, 209, 57, 27);
		panel.add(passwordLabel);
		
		JLabel descLabel = new JLabel("Entry Desc:");
		descLabel.setBounds(96, 101, 67, 27);
		panel.add(descLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(96, 154, 67, 27);
		panel.add(emailLabel);
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setBounds(96, 263, 57, 27);
		panel.add(dateLabel);
		
		entryText = new JTextField();
		entryText.setForeground(Color.BLACK);
		entryText.setBounds(173, 100, 214, 30);
		panel.add(entryText);
		entryText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setForeground(Color.BLACK);
		emailText.setBounds(173, 153, 214, 30);
		panel.add(emailText);
		emailText.setColumns(10);
		
		pswdText = new JTextField();
		pswdText.setForeground(Color.BLACK);
		pswdText.setBounds(173, 208, 214, 30);
		panel.add(pswdText);
		pswdText.setColumns(10);
		
		dateText = new JTextField();
		dateText.setForeground(Color.BLACK);
		dateText.setBounds(173, 262, 214, 30);
		panel.add(dateText);
		dateText.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Credentials Entry Database");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_4.setBounds(417, 10, 288, 61);
		frame.getContentPane().add(lblNewLabel_4);
		
		Panel panel_1 = new Panel();
		panel_1.setForeground(new Color(211, 211, 211));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(544, 107, 627, 464);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 607, 444);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				entryText.setText(model.getValueAt(i,0).toString());
				emailText.setText(model.getValueAt(i,1).toString());
				pswdText.setText(model.getValueAt(i,2).toString());
				dateText.setText(model.getValueAt(i,3).toString());
			}
		});
		model = new DefaultTableModel();
		Object[] column = {"Entry Desc.", "Email", "Password", "Date"};
		Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(291, 319, 96, 21);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				
				if(i>0) {
					model.removeRow(i);
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Row to Delete First");
				}
				
				
			}
		});
		btnDelete.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_5 = new JLabel("Credential Form\r\n");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(27, 10, 114, 27);
		panel.add(lblNewLabel_5);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.BLACK);
		
	
		btnAdd.setBounds(173, 319, 96, 21);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLACK);
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=table.getSelectedRow();
				model.setValueAt(entryText.getText(), i, 0);
				model.setValueAt(emailText.getText(), i, 1);
				model.setValueAt(pswdText.getText(), i, 2);
				model.setValueAt(dateText.getText(), i, 3);
			}
		});
		btnUpdate.setBounds(173, 350, 96, 21);
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.BLACK);
		
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entryText.setText("");
				emailText.setText("");
				pswdText.setText("");
				dateText.setText("");
			}
		});
		btnClear.setBounds(291, 350, 96, 21);
		panel.add(btnClear);
		
		JButton btnConnectDB = new JButton("Connect to DB");
		btnConnectDB.setForeground(Color.BLACK);
		btnConnectDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				
				try {
					conn = DriverManager.getConnection(url, username, password);
					JOptionPane.showMessageDialog(null, "Connected to the database");
				} catch(SQLException e) {
					JOptionPane.showMessageDialog(null, "Oops, we weren't able to connect you");
					e.printStackTrace();
				}
			}
		});
		btnConnectDB.setBounds(337, 15, 147, 21);
		panel.add(btnConnectDB);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(entryText.getText().equals("") || emailText.getText().equals("") || pswdText.getText().equals("") || dateText.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
				}
				else 
				{
					row[0] = entryText.getText();
					row[1] = emailText.getText();
					row[2] = pswdText.getText();
					row[3] = dateText.getText();
					model.addRow(row);
					
					entryText.setText("");
					emailText.setText("");
					pswdText.setText("");
					dateText.setText("");
				
					
					
					try {
						
						
						String query  = "INSERT INTO credentials (Entry_Name, Email, PWord, Entry_Date) VALUES ('" + row[0] + "', '"  + row[1] + "', '" + row[2] + "', '" + row[3] + "')";
								
		
						conn = DriverManager.getConnection(url, username, password);
						Statement stmt = conn.createStatement();
						
						stmt.executeUpdate(query);
						conn.close();
						JOptionPane.showMessageDialog(null, "Data Submitted into DB!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"We are trying to process the data inserts");
						e.printStackTrace();
					}
				
					
				}
				
			}
		});
		
		
		
		
		
	}
}
