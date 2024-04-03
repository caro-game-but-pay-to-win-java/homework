package Chuong2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.io.*;
import java.util.*;
import java.math.*;

public class B2_2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B2_2 frame = new B2_2();
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
	public B2_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Giải phương trình bậc 2");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(172, 65, 371, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hệ số a:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(83, 167, 80, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hệ số b:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(83, 221, 80, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.setBounds(212, 334, 95, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kết quả");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(83, 381, 80, 33);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(198, 177, 345, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(198, 231, 345, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(198, 391, 345, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnGii = new JButton("Giải");
		btnGii.setBounds(317, 334, 95, 33);
		contentPane.add(btnGii);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setBounds(422, 334, 95, 33);
		contentPane.add(btnThot);
		
		JLabel lblNewLabel_2_1 = new JLabel("Hệ số c:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(83, 274, 80, 33);
		contentPane.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(198, 284, 345, 19);
		contentPane.add(textField_3);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		
		btnGii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = textField.getText();
				String b = textField_1.getText();
				String c = textField_3.getText();
				
				try {
					int an = Integer.valueOf(a);
					int bn = Integer.valueOf(b);
					int cn = Integer.valueOf(c);
					double delta = Math.pow(bn, 2) -  4 * an * cn;
					String msg = "";
					
					if (an == 0) {
						String x = "a phải khác 0";
						JOptionPane.showMessageDialog(new JFrame(), x, "Dialog", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						return;
					} else if (delta < 0) {
						msg = "Phương trình vô nghiệm";
					} else if (delta == 0) {
						double r = (-bn) / 2 * an;
						msg = "x1 = x2 = " + String.valueOf(r);
					} else {
						double x1 = (-bn + Math.sqrt(delta)) / 2 * an;
						double x2 = (-bn - Math.sqrt(delta)) / 2 * an;
						msg = "x1 = " + String.valueOf(x1) + ", x2 = " + String.valueOf(x2);
					}
					textField_2.setText(msg);
				} catch (Exception ex) {
					String msg = "Dữ liệu không hợp lệ!";
					JOptionPane.showMessageDialog(new JFrame(), msg, "Dialog", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					return;
				}
			}
		});
		
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
