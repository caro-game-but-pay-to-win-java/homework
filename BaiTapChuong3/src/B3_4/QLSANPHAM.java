package B3_4;

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
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;

public class QLSANPHAM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private MyConnection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLSANPHAM frame = new QLSANPHAM();
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
	public QLSANPHAM() {
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 13, 377, 290);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		setData();
	}

	void init() {
		connection = new MyConnection("QL_SANPHAM");
	}

	void setData() {
		tableModel = new DefaultTableModel();

		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM SanPham");
			ResultSetMetaData rsmd = resultSet.getMetaData();

			List<Object> columns = new ArrayList<Object>();

			for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
				columns.add(rsmd.getColumnName(i));
			}

			tableModel.setColumnIdentifiers(columns.toArray());

			while (resultSet.next()) {
				List<Object> row = new ArrayList<Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
					row.add(resultSet.getString(i));
				}
				tableModel.addRow(row.toArray());
			}
		} catch (Exception ex) {

		}

		table.setModel(tableModel);
	}
}
