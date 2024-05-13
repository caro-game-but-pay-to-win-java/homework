package Mainpacket;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class TIM_KIEM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	private SQLConnect connection = new SQLConnect("QL_DOCGIA");
	
	List<String> columns = new ArrayList<String>();
	List<List<Object>> data = new ArrayList<List<Object>>();
	
	private DefaultTableModel model = null;
	
	int r = -1;
	int c = -1;
	int status = -1;
	
	int MuonCount = 0;
	int Total = 0;
	
	Object[][] objects1 = {};
	Object[] objects2 = {};
	private JTable table;
	private JTextField search;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel rs1;
	private JLabel rs2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TIM_KIEM frame = new TIM_KIEM();
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
	public TIM_KIEM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 184, 704, 262);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM THÔNG TIN SÁCH");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(235, 25, 238, 32);
		contentPane.add(lblNewLabel);
		
		search = new JTextField();
		search.setFont(new Font("Tahoma", Font.PLAIN, 18));
		search.setBounds(183, 127, 238, 32);
		contentPane.add(search);
		search.setColumns(10);
		
		JButton tim = new JButton("TÌM");
		
		tim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tim.setBounds(431, 126, 122, 33);
		contentPane.add(tim);
		
		tim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getData(search.getText());
				rs1.setText("Tổng số sách tìm được là " + String.valueOf(Total) + " quyển.");
				rs2.setText("Hiện có " + String.valueOf(MuonCount) + " quyển chưa được mượn.");
			}
		});
		
		
		
		model = (DefaultTableModel) table.getModel();
		
		rdbtnNewRadioButton = new JRadioButton("Mã sách");
		
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(154, 63, 131, 33);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Tác giả");
		
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNewRadioButton_1.setBounds(370, 63, 131, 33);
		contentPane.add(rdbtnNewRadioButton_1);
		
		rs1 = new JLabel("");
		rs1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rs1.setBounds(196, 456, 379, 32);
		contentPane.add(rs1);
		
		rs2 = new JLabel("");
		rs2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rs2.setBounds(196, 498, 379, 32);
		contentPane.add(rs2);
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton_1.setSelected(false);
				}
			}
		});
		
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton_1.isSelected()) {
					rdbtnNewRadioButton.setSelected(false);
				}
			}
		});
		
		getData("");
	}
	
	public void getData(String search) {
		Total = 0;
		MuonCount = 0;
		try {
			columns = new ArrayList<String>();
			data = new ArrayList<List<Object>>();
			String more = "";
			if (rdbtnNewRadioButton.isSelected()) {
				more = " WHERE MASH LIKE '%" + search + "%'";
			} else if (rdbtnNewRadioButton_1.isSelected()) {
				more = " WHERE TACGIA LIKE N'%" + search + "%'";
			}
			Statement stm = connection.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT MASH as N'Mã sách', TENSH as N'Tên sách', TACGIA as N'Tác giả', LOAI as N'Loại', TINHTRANG as N'Tình trạng' FROM SACH " + more);			
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
			
			Total = rCount;
			model.setColumnIdentifiers(objects2);
			for (List<Object> row : data) {
				model.addRow(row.toArray());
			}
			table.setModel(model);
			for (int i = 0; i < rCount; ++i) {
				if (table.getModel().getValueAt(i, 4).toString().equals("Còn")) {
					MuonCount++;
				};
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
