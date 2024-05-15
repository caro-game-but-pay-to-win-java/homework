package MainPacket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Bank.BankThread;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bai4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	public static DefaultTableModel model;
	private Thread thread = new Thread(new BankThread());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai4 frame = new Bai4();
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
	public Bai4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 130, 639, 292);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRunThread = new JButton("RUN");
		
		btnRunThread.setBounds(30, 68, 97, 25);
		contentPane.add(btnRunThread);
		
		JButton btnStopThread = new JButton("STOP");
		
		btnStopThread.setBounds(158, 68, 97, 25);
		contentPane.add(btnStopThread);
		
		setModel();
		
		btnRunThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		
		btnStopThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.interrupt();
			}
		});
		
	}
	
	private void setModel() {
		this.model = new DefaultTableModel();
		this.table.setModel(this.model);
		model.setColumnIdentifiers(new Object[] {"Threadname", "Số tiền chuyển", "Chuyển từ", "Tới", "Số dư bên chuyển", "Số dư bên nhận"});
	}
}
