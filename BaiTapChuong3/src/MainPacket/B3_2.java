package MainPacket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class B3_2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private SQLConnect conn = new SQLConnect("dvdcd");
	private int status = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B3_2 frame = new B3_2();
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
	public B3_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Quản lý DVD/CD");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 7, 98, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(151, 7, 282, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("THÊM");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 183, 115, 26);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 237, 581, 215);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblTiu = new JLabel("Tiêu đề:");
		lblTiu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTiu.setBounds(10, 40, 98, 20);
		contentPane.add(lblTiu);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(151, 40, 282, 26);
		contentPane.add(textField_1);

		JLabel lblLoi = new JLabel("Loại:");
		lblLoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoi.setBounds(10, 73, 98, 20);
		contentPane.add(lblLoi);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(151, 73, 282, 26);
		contentPane.add(textField_2);

		JLabel lblNmXutBn = new JLabel("Năm xuất bản:");
		lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNmXutBn.setBounds(10, 106, 115, 20);
		contentPane.add(lblNmXutBn);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(151, 106, 282, 26);
		contentPane.add(textField_3);

		JButton btnLu = new JButton("LƯU");
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLu.setBounds(132, 183, 115, 26);
		contentPane.add(btnLu);

		JButton btnXa = new JButton("XÓA");
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXa.setBounds(257, 183, 115, 26);
		contentPane.add(btnXa);

		JButton btnTmKim = new JButton("TÌM KIẾM");
		btnTmKim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTmKim.setBounds(382, 183, 115, 26);
		contentPane.add(btnTmKim);
		QDatabase();
		btnLu.setEnabled(false);
		btnXa.setEnabled(false);
		textField.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.isEnabled()) {
					int col = table.columnAtPoint(e.getPoint());
					int row = table.rowAtPoint(e.getPoint());
					if (col >= 0 && row >= 0) {
						textField.setText(table.getValueAt(row, 0).toString());
						textField_1.setText(table.getValueAt(row, 1).toString());
						textField_2.setText(table.getValueAt(row, 2).toString());
						textField_3.setText(table.getValueAt(row, 3).toString());
						btnLu.setEnabled(true);
						btnXa.setEnabled(true);
					} else {
						btnLu.setEnabled(false);
						btnXa.setEnabled(false);
					}
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (status == 0) {
					status = 1;
					btnXa.setEnabled(false);
					btnTmKim.setEnabled(false);
					table.setEnabled(false);
					btnLu.setEnabled(true);
					btnNewButton.setText("HỦY");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
				} else if (status == 1) {
					status = 0;
					btnTmKim.setEnabled(true);
					table.setEnabled(true);
					btnLu.setEnabled(false);
					btnNewButton.setText("THÊM");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
				}
			}
		});
		btnLu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (status == 1) {
					if (textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals("")) {
						JOptionPane.showMessageDialog(new JFrame(),  "Không được để trống trường dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						String name = textField_1.getText();
						String cate = textField_2.getText();
						String year = textField_3.getText();
						Statement stm = conn.getConnection().createStatement();
						stm.execute("insert into CDDVDCollection values (N'" + name +  "', '" + cate + "', " + year + ")");
						QDatabase();
						JOptionPane.showMessageDialog(new JFrame(),  "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						table.setEnabled(true);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame(),  "Lỗi không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
						table.setEnabled(true);
					}
				} else if (status == 0) {
					try {
						String id = textField.getText();
						String name = textField_1.getText();
						String cate = textField_2.getText();
						String year = textField_3.getText();
						Statement stm = conn.getConnection().createStatement();
						stm.execute("update CDDVDCollection "
								+ "set TieuDe = '" + name + "',"
										+ "LoaiDia = '" + cate +"',"
												+ "NamXuatBan = " + year + " "
														+ "where Ma = " + id);
						QDatabase();
						JOptionPane.showMessageDialog(new JFrame(),  "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						table.setEnabled(true);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame(),  "Lỗi không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
						table.setEnabled(true);
					}
				}
				status = 0;
				btnTmKim.setEnabled(true);
				table.setEnabled(true);
				btnLu.setEnabled(false);
				btnXa.setEnabled(false);
				btnNewButton.setText("THÊM");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnXa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (table.isEnabled()) {
					String id = textField.getText();
					if (!id.equals("")) {
						try {							
							Statement stm = conn.getConnection().createStatement();
							stm.execute("delete from CDDVDCollection where Ma = " + id);
							JOptionPane.showMessageDialog(new JFrame(),  "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							QDatabase();
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(new JFrame(), "Lỗi không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				btnLu.setEnabled(false);
				btnXa.setEnabled(false);
			}
		});
		btnTmKim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				B3_2.this.setVisible(false);
				JFrame frame = new B3_2_SearchFrame(B3_2.this);
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent e) {
						B3_2.this.setVisible(true);
					}
				});
			}
		});
	}

	private void QDatabase() {
		DefaultTableModel model = new DefaultTableModel();
		try {
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(
					"select Ma as N'Mã', TieuDe as N'Tiêu đề', LoaiDia as N'Loại đĩa', NamXuatBan as N'Năm XB' from CDDVDCollection");
			ResultSetMetaData rsmd = rs.getMetaData();

			List<Object> cols = new ArrayList<Object>();

			int colCount = rsmd.getColumnCount();

			for (int i = 1; i <= colCount; ++i) {
				cols.add(rsmd.getColumnName(i));
			}
			model.setColumnIdentifiers(cols.toArray());
			while (rs.next()) {
				List<Object> currRow = new ArrayList<Object>();
				for (int i = 1; i <= colCount; ++i) {
					currRow.add(rs.getString(i));
				}
				model.addRow(currRow.toArray());
			}

			table.setModel(model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		table.setModel(model);
		table.repaint();
	}
}
