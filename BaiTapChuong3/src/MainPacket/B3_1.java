package MainPacket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class B3_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	SQLConnect conn = new SQLConnect("QLKH_Java");
	
	List<String> columns = new ArrayList<String>();
	List<List<Object>> data = new ArrayList<List<Object>>();
	
	Object[][] objs1 = {};
	Object[] objs2 = {};
	
	private int currentRow = -1;
	private int currentCol = -1;
	private int status = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B3_1 frame = new B3_1();
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
	public B3_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 452);
		this.setTitle("Bài 3.1 - Quản lý khách hàng JDBC");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 55, 634, 197);
		contentPane.add(scrollPane_1);
		
		
		
		
		JLabel lblNewLabel = new JLabel("MÃ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 265, 107, 22);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(118, 265, 218, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblHTn = new JLabel("HỌ TÊN");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHTn.setBounds(10, 297, 107, 22);
		contentPane.add(lblHTn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(118, 297, 218, 25);
		contentPane.add(textField_1);
		
		JLabel lblNmSinh = new JLabel("NĂM SINH");
		lblNmSinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNmSinh.setBounds(10, 329, 107, 22);
		contentPane.add(lblNmSinh);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(118, 329, 218, 25);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("THÊM");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 361, 134, 36);
		contentPane.add(btnNewButton);
		
		JButton btnLu = new JButton("LƯU");
		
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLu.setBounds(154, 361, 134, 36);
		contentPane.add(btnLu);
		
		JButton btnXa = new JButton("XÓA");
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXa.setBounds(298, 361, 134, 36);
		contentPane.add(btnXa);
		
		table = new JTable();
		QDatabase();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.isEnabled()) {					
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					
					if (row >= 0 && col >= 0) {
						textField.setText(table.getModel().getValueAt(row, 0).toString());
						textField_1.setText(table.getModel().getValueAt(row, 1).toString());
						textField_2.setText(table.getModel().getValueAt(row, 2).toString());
						currentRow = row;
						currentCol = col;
					}
				}
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (status == 0) {
					status = 1;
					btnNewButton.setText("HỦY");
					
					table.setEnabled(false);
					table.setCellSelectionEnabled(false);
					
					textField.setText("MÃ ĐƯỢC INSERT TỰ ĐỘNG");
					textField_1.setText("");
					textField_2.setText("");
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					btnXa.setEnabled(false);
					btnLu.setEnabled(true);
					
				}
				else if (status == 1) {
					status = 0;
					btnNewButton.setText("THÊM");
					
					table.setEnabled(true);
					table.setCellSelectionEnabled(true);
					textField_1.setEditable(false);
					textField_2.setEditable(false);
					btnXa.setEnabled(true);
					btnLu.setEnabled(false);
					
					if (currentRow >= 0 && currentCol >= 0) {
						textField.setText(table.getModel().getValueAt(currentRow, 0).toString());
						textField_1.setText(table.getModel().getValueAt(currentRow, 1).toString());
						textField_2.setText(table.getModel().getValueAt(currentRow, 2).toString());
					} else {
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					}
				}
			}
		});
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Không được để trống tên", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else if (textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Không được để trống mã", "Năm sinh", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						String value1 = textField_1.getText();
						String value2 = textField_2.getText();
						Statement stm = conn.getConnection().createStatement();
						stm.execute("insert into KhachHang values (N'" + value1 + "', " + value2 + ", 1)");
						QDatabase();
						table.repaint();
						JOptionPane.showMessageDialog(new JFrame(), "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);;
						btnNewButton.doClick();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra, vui lòng xem lại dữ liệu! Có thể tên quá dài hoặc năm sinh không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		});
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentRow >= 0 && currentCol >= 0) {
					try {
						Statement csmt = conn.getConnection().createStatement();
						csmt.execute("delete from KhachHang where MaKH = '" + table.getModel().getValueAt(currentRow, 0).toString() + "'");
						QDatabase();
						table.repaint();
					} catch (Exception ex) {
						
					}
				}
			}
		});
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		btnLu.setEnabled(false);
		scrollPane_1.setViewportView(table);
	}
	
	private void QDatabase() {
		try {
			columns = new ArrayList<String>();
			data = new ArrayList<List<Object>>();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("select MaKH as N'Mã khách hàng', TenKH as N'Tên Khách Hàng', NamSinh as N'Năm Sinh' from KhachHang");
			ResultSetMetaData rsmd = rs.getMetaData();
			int ColumnCount = rsmd.getColumnCount();
			for (int i = 1; i <= ColumnCount; ++i) {
				columns.add(rsmd.getColumnName(i));
			}
			int rCount = 0;
			while (rs.next()) {
				List<Object> currentRow = new ArrayList<Object>();
				for (int i = 1; i <= ColumnCount; ++i) {					
					currentRow.add(rs.getString(i));
				}
				data.add(currentRow);
				rCount++;
			}
			if (rCount > 0) {
				objs2 = new Object[columns.size()];
				for (int i = 0; i < columns.size(); ++i) {
					objs2[i] = columns.get(i);
				}
			}
			DefaultTableModel tableModel = new DefaultTableModel();
			table.setModel(tableModel);
			tableModel.setColumnIdentifiers(objs2);
			for (List<Object> row : data) {
				tableModel.addRow(row.toArray());
			}
			table.setModel(tableModel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
