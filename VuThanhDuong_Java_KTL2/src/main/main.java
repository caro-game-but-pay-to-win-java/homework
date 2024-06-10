package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.SQLConnect;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private SQLConnect conn = new SQLConnect("CuoiKyDB");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("STAFFS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffs staff = new staffs();
				staff.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(10, 193, 407, 134);
		contentPane.add(btnNewButton);
		
		JButton btnStores = new JButton("STORES");
		btnStores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stores store = new stores();
				store.setVisible(true);
			}
		});
		btnStores.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStores.setBounds(442, 193, 407, 134);
		contentPane.add(btnStores);
		
		JLabel lblNewLabel = new JLabel("STORE MANAGEMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 839, 168);
		contentPane.add(lblNewLabel);
	}
	
	

}
