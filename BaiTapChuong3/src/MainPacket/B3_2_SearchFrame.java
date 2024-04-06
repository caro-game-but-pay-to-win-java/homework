package MainPacket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.WindowAdapter;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class B3_2_SearchFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private static JFrame framex = new JFrame();
	
	private DefaultTableModel model = null;
	private TableRowSorter<TableModel> tblRowSorter = null;
	
	
	private SQLConnect conn = new SQLConnect("dvdcd");
	/**
	 * Create the frame.
	 */
	public B3_2_SearchFrame(JFrame frame) {
		this.framex = frame;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 718, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 684, 267);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		contentPane.add(scrollPane);
		
		JButton btnTmKim = new JButton("TÌM KIẾM");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (text.trim().length() == 0) {
					tblRowSorter.setRowFilter(null);
				} else {
					tblRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});
		btnTmKim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTmKim.setBounds(302, 26, 115, 26);
		contentPane.add(btnTmKim);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(10, 26, 282, 26);
		contentPane.add(textField);
		QDatabase();
		
		model = (DefaultTableModel) table.getModel();
		tblRowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tblRowSorter);
		
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
