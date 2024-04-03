package MainPacket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class DangKyLopNangKhieu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFTen = new JTextField();
	private JTextField txtFNamSinh;
	private JTextField txtFSDT;
	private JTextField txtFTongTien;
	private String filePath = "src\\MainPacket\\Input_de2.txt";
	private String textToSetToPrint = "";
	private Hashtable<String, Integer> hashMap = new Hashtable<String, Integer>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKyLopNangKhieu frame = new DangKyLopNangKhieu();
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
	public DangKyLopNangKhieu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		this.setTitle("Đăng ký lớp năng khiếu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		txtFTen.setBounds(94, 10, 276, 29);
		txtFTen.setColumns(30);
		contentPane.add(txtFTen);
		setContentPane(contentPane);
		
		txtFNamSinh = new JTextField();
		txtFNamSinh.setColumns(30);
		txtFNamSinh.setBounds(469, 10, 107, 29);
		contentPane.add(txtFNamSinh);
		
		txtFSDT = new JTextField();
		txtFSDT.setColumns(30);
		txtFSDT.setBounds(94, 67, 276, 29);
		contentPane.add(txtFSDT);
		
		JLabel lblNewLabel = new JLabel("Họ tên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 90, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblSinThoi = new JLabel("SĐT");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSinThoi.setBounds(10, 68, 90, 21);
		contentPane.add(lblSinThoi);
		
		JLabel lblNmSinh = new JLabel("Năm sinh");
		lblNmSinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNmSinh.setBounds(380, 11, 78, 21);
		contentPane.add(lblNmSinh);
		
		JLabel lblDanhSchKha = new JLabel("Danh sách khóa học");
		lblDanhSchKha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDanhSchKha.setBounds(10, 128, 221, 21);
		contentPane.add(lblDanhSchKha);
		
		JCheckBox cb1 = new JCheckBox("Vẽ thiếu nhi");
		cb1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb1.setBounds(6, 155, 154, 29);
		contentPane.add(cb1);
		
		JCheckBox cb2 = new JCheckBox("Cờ vua");
		cb2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb2.setBounds(247, 155, 154, 29);
		contentPane.add(cb2);
		
		JCheckBox cb3 = new JCheckBox("Khéo tay");
		cb3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb3.setBounds(6, 186, 154, 29);
		contentPane.add(cb3);
		
		JCheckBox cb4 = new JCheckBox("Cờ tướng");
		cb4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb4.setBounds(247, 186, 154, 29);
		contentPane.add(cb4);
		
		JCheckBox cb5 = new JCheckBox("Rèn chữ");
		cb5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb5.setBounds(6, 217, 154, 29);
		contentPane.add(cb5);
		
		JCheckBox cb6 = new JCheckBox("Bóng rổ");
		cb6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb6.setBounds(247, 217, 154, 29);
		contentPane.add(cb6);
		
		JLabel lblSinThoi_1 = new JLabel("Tổng tiền:");
		lblSinThoi_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSinThoi_1.setBounds(10, 253, 90, 21);
		contentPane.add(lblSinThoi_1);
		
		txtFTongTien = new JTextField();
		txtFTongTien.setEditable(false);
		txtFTongTien.setColumns(30);
		txtFTongTien.setBounds(109, 252, 276, 29);
		contentPane.add(txtFTongTien);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 291, 578, 262);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u0111\u0103ng k\u00FD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtrInfo = new JTextArea();
		txtrInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtrInfo.select(0,  0);
		panel.add(txtrInfo);
		
		JButton btnTinh = new JButton("Tính học phí");
		btnTinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFTen.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Tên không được bỏ trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (txtFNamSinh.getText() == "") {
					JOptionPane.showMessageDialog(new JFrame(), "Năm sinh không được bỏ trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (txtFSDT.getText() == "") {
					JOptionPane.showMessageDialog(new JFrame(), "Số điện thoại không được bỏ trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String name = txtFTen.getText();
				String year = txtFNamSinh.getText();
				String phone = txtFSDT.getText();
				
				String info = "HS: " + name + ", năm sinh: " + year + ", Số ĐT Liên lạc: " + phone;
				
				int count = 0;
				Integer sum = 0;
				
				String subjects = "";
				if (cb1.isSelected()) {
					++count;
					Integer data = hashMap.get("Vẽ thiếu nhi");
					sum += data;
					subjects += "\n Vẽ thiếu nhi: " + data.toString() + "đ";
				}
				if (cb2.isSelected()) {
					++count;
					Integer data = hashMap.get("Cờ vua");
					sum += data;
					subjects += "\n Cờ vua: " + data.toString() + "đ";
				}
				if (cb3.isSelected()) {
					++count;
					Integer data = hashMap.get("Khéo tay");
					sum += data;
					subjects += "\n Khéo tay: " + data.toString() + "đ";
				}
				if (cb4.isSelected()) {
					++count;
					Integer data = hashMap.get("Cờ tướng");
					sum += data;
					subjects += "\n Cờ tướng: " + data.toString() + "đ";
				}
				if (cb5.isSelected()) {
					++count;
					Integer data = hashMap.get("Rèn chữ");
					sum += data;
					subjects += "\n Rèn chữ: " + data.toString() + "đ";
				}
				if (cb6.isSelected()) {
					++count;
					Integer data = hashMap.get("Bóng rổ");
					sum += data;
					subjects += "\n Bóng rổ: " + data.toString() + "đ";
				}
				
				Date date = new Date();
				String cSubjects = "\n (Đăng ký " + count + " môn - Ngày " + date.getDay() + "/" + date.getMonth() + "/" + date.getYear() + ")";
				
				Integer discount = 0;
				String discountStr = "";
				
				if (count >= 3) {
					discount = (int)(sum * 0.1);
					sum -= discount;
					discountStr += "\n Tiền giảm (10%): " + discount.toString() + "đ";
				}
				
				String total = "\nTổng tiền: " + sum.toString() + "đ";
				String print = info + cSubjects + subjects + discountStr + total;
				textToSetToPrint = print;
				txtFTongTien.setText(sum.toString() + "đ");
			}
		});
		btnTinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnTinh.setBounds(20, 563, 128, 29);
		contentPane.add(btnTinh);
		
		JButton btnIn = new JButton("In");
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textToSetToPrint.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Chưa có dữ liệu để in, vui lòng tính trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				txtrInfo.setText(textToSetToPrint);
			}
		});
		btnIn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnIn.setBounds(160, 563, 128, 29);
		contentPane.add(btnIn);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFTen.setText("");
				txtFNamSinh.setText("");
				txtFSDT.setText("");
				txtFTongTien.setText("");
				txtrInfo.setText("");
				textToSetToPrint = "";
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnXoa.setBounds(298, 563, 128, 29);
		contentPane.add(btnXoa);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(new JFrame(), "Bạn có muốn thoát?", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
				if (res == 0) {
					System.exit(res);
				}
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThoat.setBounds(436, 563, 128, 29);
		contentPane.add(btnThoat);
		
		readFile();
	}
	
	private void readFile() {
		try {			
			File file = new File(filePath);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] words = line.split(",");
				int val = Integer.parseInt(words[1]);
				hashMap.put(words[0], val);
				System.out.println(val);
				System.out.println(words[0]);
			}
			reader.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			File file = new File(".");
			for(String fileNames : file.list()) System.out.println(fileNames);
			JOptionPane.showMessageDialog(new JFrame(), "Có lỗi xảy ra khi đọc File!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
