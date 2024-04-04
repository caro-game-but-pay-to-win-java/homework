package MainPacket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class B3_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	SQLConnect conn = new SQLConnect();
	
	List<String> columns = new ArrayList<String>();
	List<List<Object>> data = new ArrayList<List<Object>>();
	
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
		QDatabase();
		Object[][] objs1 = {};
		Object[] objs2 = {};
		objs1 = new Object[data.size()][data.get(0).size()];
		for (int i = 0; i < data.size(); ++i) {
			for (int j = 0; j < data.get(0).size(); ++j) {
				objs1[i][j] = data.get(i).get(j);
			}
		}
		
		objs2 = new Object[columns.size()];
		for (int i = 0; i < columns.size(); ++i) {
			objs2[i] = columns.get(i);
		}
		
		table = new JTable(objs1, objs2);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				
				if (row >= 0 && col >= 0) {
					textField.setText(table.getModel().getValueAt(row, 0).toString());
					textField_1.setText(table.getModel().getValueAt(row, 1).toString());
					textField_2.setText(table.getModel().getValueAt(row, 2).toString());
				}
				
			}
		});
		scrollPane_1.setViewportView(table);
		
		
	}
	
	private void QDatabase() {
		try {
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("select MaKH as N'Mã khách hàng', TenKH as N'Tên Khách Hàng', NamSinh as N'Năm Sinh' from KhachHang");
			ResultSetMetaData rsmd = rs.getMetaData();
			int ColumnCount = rsmd.getColumnCount();
			for (int i = 1; i <= ColumnCount; ++i) {
				columns.add(rsmd.getColumnName(i));
			}
			
			while (rs.next()) {
				List<Object> currentRow = new ArrayList<Object>();
				for (int i = 1; i <= ColumnCount; ++i) {					
					currentRow.add(rs.getString(i));
				}
				data.add(currentRow);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
