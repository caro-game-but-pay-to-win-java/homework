package MainPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class FrameWorkManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField new_Work;
	private JTextField current_Work;
	private JTable table;
	
	private Integer currId = 1;
	private Integer status = 0;
	private Work currentWork = null;
	private JLabel start_Time = new JLabel("");
	private Boolean Norr = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWorkManagement frame = new FrameWorkManagement();
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
	public FrameWorkManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton start_Work_Button = new JButton("BẮT ĐẦU CÔNG VIỆC MỚI");
		
		start_Work_Button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		start_Work_Button.setBounds(688, 113, 215, 33);
		contentPane.add(start_Work_Button);
		
		new_Work = new JTextField();
		new_Work.setFont(new Font("Tahoma", Font.PLAIN, 13));
		new_Work.setBounds(201, 70, 702, 33);
		contentPane.add(new_Work);
		new_Work.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tên công việc mới:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 70, 144, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thời gian hiện tại:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(20, 113, 144, 33);
		contentPane.add(lblNewLabel_1);
		
		TimeLabel current_Time = new TimeLabel();
		current_Time.setFont(new Font("Tahoma", Font.PLAIN, 13));
		current_Time.setBounds(201, 113, 144, 33);
		contentPane.add(current_Time);
		
		JLabel lblCngVicHin = new JLabel("Công việc hiện tại:");
		lblCngVicHin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCngVicHin.setBounds(20, 182, 144, 33);
		contentPane.add(lblCngVicHin);
		
		JLabel lblNewLabel_1_2 = new JLabel("Thời điểm thực hiện:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(20, 225, 144, 33);
		contentPane.add(lblNewLabel_1_2);
		
		current_Work = new JTextField();
		current_Work.setEditable(false);
		current_Work.setFont(new Font("Tahoma", Font.PLAIN, 13));
		current_Work.setColumns(10);
		current_Work.setBounds(201, 182, 702, 33);
		contentPane.add(current_Work);
		
		
		start_Time.setFont(new Font("Tahoma", Font.PLAIN, 13));
		start_Time.setBounds(201, 225, 144, 33);
		contentPane.add(start_Time);
		
		JButton finish_Work_Button = new JButton("KẾT THÚC CÔNG VIỆC");
		finish_Work_Button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		finish_Work_Button.setBounds(688, 225, 215, 33);
		contentPane.add(finish_Work_Button);
		
		JLabel lblDanhSchCng = new JLabel("Danh sách công việc đã làm trong ngày:");
		lblDanhSchCng.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDanhSchCng.setBounds(20, 304, 279, 33);
		contentPane.add(lblDanhSchCng);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 354, 893, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton exit_Button = new JButton("THOÁT ỨNG DỤNG");
		exit_Button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		exit_Button.setBounds(688, 640, 215, 33);
		contentPane.add(exit_Button);
		
		LogFileChecking();
		
		start_Work_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new_Work.getText().length() > 100 || new_Work.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Không được để tên quá dài hoặc để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else if (status == 1) {
					JOptionPane.showMessageDialog(new JFrame(), "Hãy kết thúc công việc cũ trước khi bắt đầu công việc mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    } else {
					String new_work_string = new_Work.getText();
					Work work = new Work(currId, new_work_string, LocalTime.now());
					JOptionPane.showMessageDialog(new JFrame(), "Thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					currentWork = work;
					
					status = 1;
					currId += 1;
					
					current_Work.setText(currentWork.getWorkName());
					start_Time.setText(currentWork.getWorkStartTime().toString());
					
				}
				new_Work.setText("");
			}
		});
		finish_Work_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentWork != null) {					
					currentWork.setFinishTime();
					currentWork.writeLogFile();
					current_Work.setText("");
					start_Time.setText("");
					status = 0;
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String workName = currentWork.getWorkName();
					String startTime = currentWork.getWorkStartTime().toString();
					String finishTime = currentWork.getWorkFinishTime().toString();
					Object[] row = {workName, startTime, finishTime};
					model.addRow(row);
					currentWork = null;
				}
			}
		});
		exit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnExit();
			}
		});
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	OnExit();
            }
        });
	}
	
	private void OnExit() {
		Object[] options = {"Đồng ý", "Bỏ qua", "Hủy"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
		"Bạn có muốn thoát?",
		"Hỏi",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,     //do not use a custom Icon
		options,  //the titles of buttons
		options[0]); //default button title
		
		if (n == 0) {
			if (currentWork != null) {					
				currentWork.setFinishTime();
				currentWork.writeLogFile();
				currentWork = null;
				current_Work.setText("");
				start_Time.setText("");
				status = 0;
			}
			System.exit(0);
		} else if (n == 1) {
			if (Norr == false) {
			if (currentWork != null) {					
				currentWork.writeLogFile();
				currentWork = null;
				current_Work.setText("");
				start_Time.setText("");
				status = 0;
			} }
			System.exit(0);
		} else if (n == 2) {
			
		}
	}
	
	private void LogFileChecking() {
		DefaultTableModel model = new DefaultTableModel();
		Object[] objs = {"Công việc", "Thời gian bắt đầu", "Thời gian kết thúc"};
		model.setColumnIdentifiers(objs);
		try {
			String fileName = "src\\MainPackage\\" + Work.getNameOfLogFile(LocalDate.now());
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			} else {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] outputs = line.split("#");
					Integer max = 1;
					Integer curr = Integer.parseInt(outputs[0]);
					if (curr > max) {
						max = curr;
						currId = max + 1;
					}
					if (outputs.length == 3 && !scanner.hasNextLine()) {
						current_Work.setText(outputs[1]);
						String[] ops = outputs[2].split(":");
						Integer id = Integer.parseInt(outputs[0]);
						Integer hours = Integer.parseInt(ops[0]);
						Integer minutes = Integer.parseInt(ops[1]);
						Integer seconds = Integer.parseInt(ops[2]);
						LocalTime time = LocalTime.of(hours, minutes, seconds);
						currentWork = new Work(id, outputs[1], time);
						current_Work.setText(currentWork.getWorkName());
						start_Time.setText(currentWork.getWorkStartTime().toString());
						status = 1;
						Norr = true;
					} else if (outputs.length == 4) {
						String workName = outputs[1];
						String startTime = outputs[2];
						String finishTime = outputs[3];
						Object[] row = {workName, startTime, finishTime};
						model.addRow(row);
					}
				}
			}
			this.table.setModel(model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
