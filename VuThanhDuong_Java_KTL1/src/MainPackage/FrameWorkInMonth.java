package MainPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JTable;

public class FrameWorkInMonth extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JLabel lblNewLabel = new JLabel("DANH SÁCH CÔNG VIỆC ĐÃ LÀM TRONG THÁNG");
	JLabel lblNewLabel_1 = new JLabel("TỔNG THỜI GIAN THỰC HIỆN:");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWorkInMonth frame = new FrameWorkInMonth();
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
	public FrameWorkInMonth() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 860, 447);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 850, 52);
		contentPane.add(lblNewLabel);
		
		
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 529, 464, 33);
		contentPane.add(lblNewLabel_1);
		ReadFileInMonth();
	}
	
	private void ReadFileInMonth() {
		String currentMonth = LocalDate.now().getMonth().toString();
		lblNewLabel.setText(lblNewLabel.getText() + " " + currentMonth);
		DefaultTableModel model = new DefaultTableModel();
		Object[] objs = {"Công việc", "Thời gian bắt đầu", "Thời gian kết thúc"};
		model.setColumnIdentifiers(objs);
		try {
			File dictionary = new File("src//MainPackage");
			File[] files = dictionary.listFiles();
			List<File> list = new ArrayList<File>();
			for (File file : files) {
				if (file.getName().contains(currentMonth)) {
					list.add(file);
				}
			}
			for(File file: list) {
				Duration duration = Duration.ZERO;
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] outputs = line.split("#");
					if (outputs.length == 3) {
						
					} else {
						String[] ops = outputs[2].split(":");
						Integer hours = Integer.parseInt(ops[0]);
						Integer minutes = Integer.parseInt(ops[1]);
						Integer seconds = Integer.parseInt(ops[2]);
						LocalTime timeStart = LocalTime.of(hours, minutes, seconds);
						System.out.println(timeStart.toString());
						String[] ops2 = outputs[3].split(":");
						Integer hours2 = Integer.parseInt(ops2[0]);
						Integer minutes2 = Integer.parseInt(ops2[1]);
						Integer seconds2 = Integer.parseInt(ops2[2]);
						LocalTime timeFinish = LocalTime.of(hours2, minutes2, seconds2);
						System.out.println(timeFinish.toString());
						duration = duration.plus(Duration.between(timeStart, timeFinish));
						
						String workName = outputs[1];
						String startTime = outputs[2];
						String finishTime = outputs[3];
						Object[] row = {workName, startTime, finishTime};
						model.addRow(row);
					}
				}
				System.out.println(duration.toString());
				
				lblNewLabel_1.setText(lblNewLabel_1.getText() + " " + format(duration));
				
			}
			table.setModel(model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static String format(Duration duration) {
	    return String.format("%02d:%02d:%02d", duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
	}

}
