package Mainpacket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QUAN_LY_THU_VIEN extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private SQLConnect connection = new SQLConnect("QL_DOCGIA");
	
	List<String> columns = new ArrayList<String>();
	List<List<Object>> data = new ArrayList<List<Object>>();
	
	int r = -1;
	int c = -1;
	int status = -1;
	
	Object[][] objects1 = {};
	Object[] objects2 = {};
	
	private JTextField masach;
	private JTextField tensach;
	private JTextField tacgia;
	private JTextField loai;
	private JTextField tinhtrang;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QUAN_LY_THU_VIEN frame = new QUAN_LY_THU_VIEN();
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
	public QUAN_LY_THU_VIEN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN SÁCH");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(113, 111, 182, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblTt = new JLabel("Mã sách");
		lblTt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTt.setBounds(26, 157, 77, 36);
		contentPane.add(lblTt);
		
		JLabel lblTt_1 = new JLabel("Tên sách");
		lblTt_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTt_1.setBounds(26, 203, 77, 36);
		contentPane.add(lblTt_1);
		
		masach = new JTextField();
		masach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		masach.setBounds(113, 157, 222, 36);
		contentPane.add(masach);
		masach.setColumns(10);
		
		tensach = new JTextField();
		tensach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tensach.setColumns(10);
		tensach.setBounds(113, 203, 222, 36);
		contentPane.add(tensach);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTcGi.setBounds(345, 157, 77, 36);
		contentPane.add(lblTcGi);
		
		tacgia = new JTextField();
		tacgia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tacgia.setColumns(10);
		tacgia.setBounds(432, 157, 222, 36);
		contentPane.add(tacgia);
		
		loai = new JTextField();
		loai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loai.setColumns(10);
		loai.setBounds(432, 203, 222, 36);
		contentPane.add(loai);
		
		JLabel lblTt_1_1 = new JLabel("Loại");
		lblTt_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTt_1_1.setBounds(345, 203, 77, 36);
		contentPane.add(lblTt_1_1);
		
		JLabel lblTt_2_1 = new JLabel("Tình trạng");
		lblTt_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTt_2_1.setBounds(665, 157, 111, 36);
		contentPane.add(lblTt_2_1);
		
		tinhtrang = new JTextField();
		tinhtrang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tinhtrang.setColumns(10);
		tinhtrang.setBounds(786, 157, 222, 36);
		contentPane.add(tinhtrang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 259, 629, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblQunLSch = new JLabel("QUẢN LÝ SÁCH");
		lblQunLSch.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		lblQunLSch.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblQunLSch.setBounds(345, 10, 289, 116);
		contentPane.add(lblQunLSch);
		
		JButton them = new JButton("Thêm");
		
		them.setFont(new Font("Tahoma", Font.PLAIN, 18));
		them.setBounds(682, 259, 164, 36);
		contentPane.add(them);
		
		JButton luu = new JButton("Lưu");
		
		luu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		luu.setBounds(856, 259, 164, 36);
		contentPane.add(luu);
		
		JButton sua = new JButton("Sửa");
		
		sua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sua.setBounds(682, 305, 164, 36);
		contentPane.add(sua);
		
		JButton huy = new JButton("Hủy");
		huy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		huy.setBounds(856, 305, 164, 36);
		contentPane.add(huy);
		
		JButton xoa = new JButton("Xóa");
		xoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		xoa.setBounds(682, 351, 164, 36);
		contentPane.add(xoa);
		
		JButton thoat = new JButton("Thoát");
		thoat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		thoat.setBounds(856, 351, 164, 36);
		contentPane.add(thoat);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.isEnabled()) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					
					if (row >= 0 && col >= 0) {
						r = row;
						c = col;
						masach.setText(table.getModel().getValueAt(row, 0).toString());
						tensach.setText(table.getModel().getValueAt(row, 1).toString());
						tacgia.setText(table.getModel().getValueAt(row, 2).toString());
						loai.setText(table.getModel().getValueAt(row, 3).toString());
						tinhtrang.setText(table.getModel().getValueAt(row, 4).toString());
						sua.setEnabled(true);
						xoa.setEnabled(true);
					}
				}
			}
		});
		
		
		
		luu.setEnabled(false);
		huy.setEnabled(false);
		sua.setEnabled(false);
		xoa.setEnabled(false);
		
		setNotEditable(false);
		getData();
		
		them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = 1;
				setNotEditable(true);
				setTextx();
				them.setEnabled(false);
				sua.setEnabled(false);
				xoa.setEnabled(false);
				luu.setEnabled(true);
				huy.setEnabled(true);
			}
		});
		luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (status == 1) {
					String x = isViolated();
					if (!x.equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), x + " không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							String ms = masach.getText();
							String ts = tensach.getText();
							String tg = tacgia.getText();
							String l = loai.getText();
							String tt = tinhtrang.getText();
							
							Statement stm = connection.getConnection().createStatement();
							stm.execute("insert into SACH values('" + ms + "',N'" + ts + "',N'" + tg + "',N'" + l + "',N'" + tt + "')");
							getData();
							JOptionPane.showMessageDialog(new JFrame(), "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							
							setHuy();
							them.setEnabled(true);
							sua.setEnabled(false);
							xoa.setEnabled(false);
							luu.setEnabled(false);
							huy.setEnabled(false);
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(new JFrame(), "Lỗi dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
							setHuy();
							them.setEnabled(true);
							sua.setEnabled(false);
							xoa.setEnabled(false);
							luu.setEnabled(false);
							huy.setEnabled(false);
						}
					}
				} else if (status == 2) {
					String x = isViolated();
					if (!x.equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), x + " không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							String ms = masach.getText();
							String ts = tensach.getText();
							String tg = tacgia.getText();
							String l = loai.getText();
							String tt = tinhtrang.getText();
							
							Statement stm = connection.getConnection().createStatement();
							stm.execute("update SACH set "
									+ "TENSH = N'" + ts + "',"
									+ "TACGIA = N'" + tg + "',"
									+ "LOAI = N'" + l + "',"
									+ "TINHTRANG = N'" + tt + "' "
									+ "WHERE MASH = '" + ms +  "'"
							);
							getData();
							JOptionPane.showMessageDialog(new JFrame(), "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							
							setHuy();
							them.setEnabled(true);
							sua.setEnabled(false);
							xoa.setEnabled(false);
							luu.setEnabled(false);
							huy.setEnabled(false);
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(new JFrame(), "Lỗi dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
							setHuy();
							them.setEnabled(true);
							sua.setEnabled(false);
							xoa.setEnabled(false);
							luu.setEnabled(false);
							huy.setEnabled(false);
						}
					}
				}
			}
		});
		sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = 2;
				setNotEditable(true);
				them.setEnabled(false);
				sua.setEnabled(false);
				xoa.setEnabled(false);
				luu.setEnabled(true);
				huy.setEnabled(true);
				masach.setEditable(false);
			}
		});
		huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setHuy();
				them.setEnabled(true);
				sua.setEnabled(false);
				xoa.setEnabled(false);
				luu.setEnabled(false);
				huy.setEnabled(false);
			}
		});
		xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ms = masach.getText();
					
					Statement stm = connection.getConnection().createStatement();
					stm.execute("delete from SACH where MASH = '" + ms + "'");
					getData();
					JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					setHuy();
					them.setEnabled(true);
					sua.setEnabled(false);
					xoa.setEnabled(false);
					luu.setEnabled(false);
					huy.setEnabled(false);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(), "Lỗi dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					setHuy();
					them.setEnabled(true);
					sua.setEnabled(false);
					xoa.setEnabled(false);
					luu.setEnabled(false);
					huy.setEnabled(false);
				}
			}
		});
		
		thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(new JFrame(), "Bạn có chắc muốn thoát?", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);
				if (i == 0) {
					System.exit(0);
				} else if (i == 1) {
					
				} else {
					
				}
			}
		});
	}
	
	public void setNotEditable(boolean x) {
		table.setEnabled(!x);
		masach.setEditable(x);
		tensach.setEditable(x);
		tacgia.setEditable(x);
		loai.setEditable(x);
		tinhtrang.setEditable(x);
	}
	
	public void setTextx() {
		masach.setText("");
		tensach.setText("");
		tacgia.setText("");
		loai.setText("");
		tinhtrang.setText("");
	}
	
	public String isViolated() {
		if (masach.getText().equals(""))
			return "Mã sách";
		if (tensach.getText().equals(""))
			return "Tên sách";
		if (tacgia.getText().equals(""))
			return "Tác giả";
		if (loai.getText().equals(""))
			return "Loại";
		if (tinhtrang.getText().equals(""))
			return "Tình trạng";
		return "";
	}
	
	public void setHuy() {
		if (r != -1) {
			masach.setText(table.getModel().getValueAt(r, 0).toString());
			tensach.setText(table.getModel().getValueAt(r, 1).toString());
			tacgia.setText(table.getModel().getValueAt(r, 2).toString());
			loai.setText(table.getModel().getValueAt(r, 3).toString());
			tinhtrang.setText(table.getModel().getValueAt(r, 4).toString());
		} else {
			setTextx();
		}
		setNotEditable(false);
	}
	
	public void setButtonsEnabled(boolean x) {
		
	}
	
	public void getData() {
		try {
			columns = new ArrayList<String>();
			data = new ArrayList<List<Object>>();
			Statement stm = connection.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT MASH as N'Mã sách', TENSH as N'Tên sách', TACGIA as N'Tác giả', LOAI as N'Loại', TINHTRANG as N'Tình trạng' FROM SACH");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; ++i) {
				columns.add(rsmd.getColumnName(i));
			}
			
			int rCount = 0;
			while (rs.next()) {
				List<Object> currentRow = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; ++i) {
					currentRow.add(rs.getString(i));
				}
				data.add(currentRow);
				rCount++;
			}
			
			if (rCount > 0) {
				objects2 = new Object[columns.size()];
				for (int i = 0; i < columnCount; ++i) {
					objects2[i] = columns.get(i);
				}
			}
			
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			
			model.setColumnIdentifiers(objects2);
			for (List<Object> row : data) {
				model.addRow(row.toArray());
			}
			table.setModel(model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
