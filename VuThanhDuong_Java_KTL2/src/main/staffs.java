package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.SQLConnect;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class staffs extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField tfId;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfFirstName;
	private JTextField tfLastName;

	private int status = 0; // 1 - add, 2 - save, 3 - delete

	JComboBox<String> cbManagerId = new JComboBox<String>();
	JComboBox<String> cbStoreId = new JComboBox<String>();
	JComboBox<String> cbActive = new JComboBox<String>();

	JButton btnClose = new JButton("CLOSE");
	JButton btnSave = new JButton("SAVE");
	JButton btnAbort = new JButton("CANCEL");
	JButton btnUpdate = new JButton("UPDATE");
	JButton btnAdd = new JButton("ADD");
	JButton btnDelete = new JButton("DELETE");

	private SQLConnect conn = new SQLConnect("CuoiKyDB");

	private final JLabel lblActive_1 = new JLabel("filter store_id");
	private final JComboBox<String> cbfStoreId = new JComboBox<String>();
	private final JLabel lblActive_1_1 = new JLabel("filter manager_id");
	private final JComboBox<String> cbfManagerId = new JComboBox<String>();
	private final JLabel lblActive_1_2 = new JLabel("sort by store_id");
	private final JComboBox<String> cbsStoreId = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffs frame = new staffs();
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
	public staffs() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 861, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 707, 348);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBounds(727, 191, 103, 44);
		contentPane.add(btnAdd);

		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(727, 245, 103, 44);
		contentPane.add(btnDelete);

		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(727, 299, 103, 44);
		contentPane.add(btnUpdate);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 12, 103, 19);
		contentPane.add(lblNewLabel);

		tfId = new JTextField();
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfId.setBounds(125, 10, 254, 22);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhone.setBounds(10, 43, 103, 19);
		contentPane.add(lblPhone);

		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPhone.setColumns(10);
		tfPhone.setBounds(125, 41, 254, 22);
		contentPane.add(tfPhone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(10, 74, 103, 19);
		contentPane.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfEmail.setColumns(10);
		tfEmail.setBounds(125, 72, 254, 22);
		contentPane.add(tfEmail);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(389, 12, 103, 19);
		contentPane.add(lblFirstName);

		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(504, 10, 254, 22);
		contentPane.add(tfFirstName);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(389, 42, 103, 19);
		contentPane.add(lblLastName);

		tfLastName = new JTextField();
		tfLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfLastName.setColumns(10);
		tfLastName.setBounds(504, 40, 254, 22);
		contentPane.add(tfLastName);

		JLabel lblManagerId = new JLabel("Manager ID");
		lblManagerId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManagerId.setBounds(389, 73, 103, 19);
		contentPane.add(lblManagerId);

		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActive.setBounds(10, 105, 103, 19);
		contentPane.add(lblActive);

		JLabel lblStoreId = new JLabel("Store ID");
		lblStoreId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStoreId.setBounds(389, 104, 103, 19);
		contentPane.add(lblStoreId);

		cbActive.setBounds(125, 104, 254, 23);
		contentPane.add(cbActive);

		cbManagerId.setBounds(504, 72, 254, 23);
		contentPane.add(cbManagerId);

		cbStoreId.setBounds(504, 104, 254, 23);
		contentPane.add(cbStoreId);

		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSave.setBounds(728, 353, 103, 44);
		contentPane.add(btnSave);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(727, 495, 103, 44);
		contentPane.add(btnClose);

		btnAbort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAbort.setBounds(728, 407, 103, 44);
		contentPane.add(btnAbort);
		lblActive_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActive_1.setBounds(10, 159, 118, 19);

		contentPane.add(lblActive_1);

		cbfStoreId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString().equals("Tất cả")) {
					onLoad();
				} else {
					onLoadByStoreId(e.getItem().toString());
				}
			}
		});

		cbfManagerId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString().equals("Tất cả")) {
					onLoad();
				} else {
					onLoadByManagerId(e.getItem().toString());
				}
			}
		});

		cbsStoreId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString().equals("Tăng dần")) {
					onLoad(true);
				} else {
					onLoad(false);
				}
			}
		});

		cbfStoreId.setEnabled(true);
		cbfStoreId.setBounds(138, 158, 86, 23);

		contentPane.add(cbfStoreId);
		lblActive_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActive_1_1.setBounds(234, 162, 145, 19);

		contentPane.add(lblActive_1_1);

		cbfManagerId.setEnabled(true);
		cbfManagerId.setBounds(373, 158, 86, 23);

		contentPane.add(cbfManagerId);
		lblActive_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActive_1_2.setBounds(469, 162, 137, 19);

		contentPane.add(lblActive_1_2);

		cbsStoreId.setEnabled(true);
		cbsStoreId.setBounds(598, 158, 119, 23);

		contentPane.add(cbsStoreId);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLock();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDelete(tfId.getText());
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLock();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLock();
			}
		});

		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abort();
			}
		});

		onLoad();
		init();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.isEnabled()) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());

					if (row >= 0 && col >= 0) {
						try {
							tfId.setText(table.getValueAt(row, 0).toString());
							tfFirstName.setText(table.getValueAt(row, 1).toString());
							tfLastName.setText(table.getValueAt(row, 2).toString());
							tfEmail.setText(table.getValueAt(row, 3).toString());
							tfPhone.setText(table.getValueAt(row, 4).toString());
							cbActive.setSelectedItem(table.getValueAt(row, 5).toString());
							cbManagerId.setSelectedItem(table.getValueAt(row, 6).toString());
							cbStoreId.setSelectedItem(table.getValueAt(row, 7).toString());

							btnUpdate.setEnabled(true);
							btnDelete.setEnabled(true);
						} catch (Exception ex) {

						}
					}
				}
			}
		});
	}

	void onDelete(String id) {
		int del = JOptionPane.showConfirmDialog(new JFrame(), "Bạn có chắc muốn xóa?", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (del == 0) {
			try {
				Statement stm = conn.getConnection().createStatement();
				stm.executeUpdate("DELETE FROM STAFFS WHERE STAFF_ID=" + tfId.getText());
				JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công!", "Thông báo!",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			onLoad();
		} else {
			return;
		}
	}

	void init() {
		try {
			cbsStoreId.addItem("Tăng dần");
			cbsStoreId.addItem("Giảm dần");

			cbActive.addItem("0");
			cbActive.addItem("1");
			// second
			cbfManagerId.addItem("Tất cả");
			cbfStoreId.addItem("Tất cả");
			Statement stm1 = conn.getConnection().createStatement();
			ResultSet rs1 = stm1.executeQuery("SELECT staff_id from STAFFS order by staff_id asc");
			while (rs1.next()) {
				cbManagerId.addItem(rs1.getString(1));
				cbfManagerId.addItem(rs1.getString(1));
			}
			// third

			Statement stm2 = conn.getConnection().createStatement();
			ResultSet rs2 = stm2.executeQuery("SELECT store_id from STORES order by store_id asc");
			while (rs2.next()) {
				cbStoreId.addItem(rs2.getString(1));
				cbfStoreId.addItem(rs2.getString(1));
			}
		} catch (Exception ex) {

		}

		tfId.setEnabled(false);
		tfFirstName.setEnabled(false);
		tfLastName.setEnabled(false);
		tfEmail.setEnabled(false);
		tfPhone.setEnabled(false);
		cbActive.setEnabled(false);
		cbStoreId.setEnabled(false);
		cbManagerId.setEnabled(false);

		btnAbort.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnSave.setEnabled(false);
	}

	void setEditable() {
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
	}

	void addLock() {
		table.setEnabled(false);
		status = 1;
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);

		btnAbort.setEnabled(true);
		btnSave.setEnabled(true);

		tfId.setText("");
		tfFirstName.setText("");
		tfLastName.setText("");
		tfEmail.setText("");
		tfPhone.setText("");

		cbActive.setSelectedIndex(-1);
		cbStoreId.setSelectedIndex(-1);
		cbManagerId.setSelectedIndex(-1);

		tfFirstName.setEnabled(true);
		tfLastName.setEnabled(true);
		tfEmail.setEnabled(true);
		tfPhone.setEnabled(true);
		cbActive.setEnabled(true);
		cbStoreId.setEnabled(true);
		cbManagerId.setEnabled(true);
	}

	void updateLock() {
		table.setEnabled(false);
		status = 2;

		btnAdd.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnSave.setEnabled(true);
		btnAbort.setEnabled(true);

		tfEmail.setEnabled(true);
		tfPhone.setEnabled(true);
		cbActive.setEnabled(true);
		cbStoreId.setEnabled(true);
		cbManagerId.setEnabled(true);
	}

	void saveLock() {
		if (status == 1) {
			Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
					Pattern.CASE_INSENSITIVE);
			Pattern phonePattern = Pattern.compile("^\\d{10}$");

			if (tfFirstName.getText().equals("") || tfLastName.getText().equals("") || tfEmail.getText().equals("")
					|| tfPhone.getText().equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Không được để trống bất kỳ trường nào!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (cbActive.getSelectedItem() == null || cbStoreId.getSelectedItem() == null
					|| cbManagerId.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(new JFrame(), "Không được để trống bất kỳ trường nào!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!emailPattern.matcher(tfEmail.getText()).find()) {
				JOptionPane.showMessageDialog(new JFrame(), "Định dạng Email không phù hợp", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!phonePattern.matcher(tfPhone.getText()).find()) {
				JOptionPane.showMessageDialog(new JFrame(), "Định dạng Phone không phù hợp", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				try {
					Statement stm = conn.getConnection().createStatement();
					stm.executeUpdate(
							"INSERT INTO STAFFS (FIRST_NAME, LAST_NAME, EMAIL, PHONE, ACTIVE, STORE_ID, MANAGER_ID) VALUES ('"
									+ tfFirstName.getText() + "', '" + tfLastName.getText() + "', '" + tfEmail.getText()
									+ "', '" + tfPhone.getText() + "', " + cbActive.getSelectedItem().toString() + ", "
									+ cbStoreId.getSelectedItem().toString() + ", "
									+ cbManagerId.getSelectedItem().toString() + ")");
					JOptionPane.showMessageDialog(new JFrame(), "Thêm thành công!", "Thông báo!",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra! Kiểm tra độ dài chuỗi!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (status == 2) {
			Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
					Pattern.CASE_INSENSITIVE);
			if (tfFirstName.getText().equals("") || tfLastName.getText().equals("") || tfEmail.getText().equals("")
					|| tfPhone.getText().equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Không được để trống bất kỳ trường nào!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (cbActive.getSelectedItem() == null || cbStoreId.getSelectedItem() == null
					|| cbManagerId.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(new JFrame(), "Không được để trống bất kỳ trường nào!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!emailPattern.matcher(tfEmail.getText()).find()) {
				JOptionPane.showMessageDialog(new JFrame(), "Định dạng Email không phù hợp", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				try {
					Statement stm = conn.getConnection().createStatement();
					stm.executeUpdate("UPDATE STAFFS SET EMAIL='" + tfEmail.getText() + "', PHONE='" + tfPhone.getText()
							+ "', ACTIVE=" + cbActive.getSelectedItem().toString() + ", STORE_ID="
							+ cbStoreId.getSelectedItem().toString() + ", MANAGER_ID="
							+ cbManagerId.getSelectedItem().toString() + " WHERE STAFF_ID=" + tfId.getText());
					JOptionPane.showMessageDialog(new JFrame(), "Sửa thành công!", "Thông báo!",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra! Kiểm tra độ dài chuỗi!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {

		}
		onLoad();
		abort();
		status = 0;
	}

	void abort() {
		table.setEnabled(true);
		tfId.setEnabled(false);
		tfFirstName.setEnabled(false);
		tfLastName.setEnabled(false);
		tfEmail.setEnabled(false);
		tfPhone.setEnabled(false);
		cbActive.setEnabled(false);
		cbStoreId.setEnabled(false);
		cbManagerId.setEnabled(false);

		btnAdd.setEnabled(true);
		btnAbort.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnSave.setEnabled(false);
	}

	void onLoad() {
		try {
			DefaultTableModel model = new DefaultTableModel();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM STAFFS");
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

	void onLoadByStoreId(String store_id) {
		try {
			DefaultTableModel model = new DefaultTableModel();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM STAFFS WHERE STORE_ID=" + store_id);
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

	void onLoadByManagerId(String manager_id) {
		try {
			DefaultTableModel model = new DefaultTableModel();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM STAFFS WHERE MANAGER_ID=" + manager_id);
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

	void onLoad(Boolean isAscending) {
		try {
			String x = "DESC";
			if (isAscending) {
				x = "ASC";
			}
			DefaultTableModel model = new DefaultTableModel();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM STAFFS ORDER BY STORE_ID " + x);
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
