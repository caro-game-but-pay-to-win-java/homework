package main;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.SQLConnect;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class stores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SQLConnect conn = new SQLConnect("CuoiKyDB");
	private JTable table;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stores frame = new stores();
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
	public stores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1013, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 841, 542);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(861, 10, 138, 34);
		contentPane.add(btnNewButton);
		onLoad();
	}
	
	void onLoad() {
		try {
			DefaultTableModel model = new DefaultTableModel();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM STORES");
			ResultSetMetaData rsmd = rs.getMetaData();

			List<Object> cols = new ArrayList<Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
				cols.add(rsmd.getColumnName(i));
			}
			model.setColumnIdentifiers(cols.toArray());

			while (rs.next()) {
				List<Object> row = new ArrayList<Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
					row.add(rs.getString(i));
				}
				model.addRow(row.toArray());
			}

			table.setModel(model);

			// first

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
