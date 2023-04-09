package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public class Appt extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenSuKien;
	private JTextField txtViTri;
	JButton btnXacNhan;
	JRadioButton rdbtnDon,rdbtnNhom;
	JComboBox cbbToiDa;
	

	public Appt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên sự kiện:");
		lblNewLabel.setBounds(51, 55, 85, 13);
		contentPane.add(lblNewLabel);
		
		txtTenSuKien = new JTextField();
		txtTenSuKien.setBounds(163, 52, 127, 19);
		contentPane.add(txtTenSuKien);
		txtTenSuKien.setColumns(10);
		
		txtViTri = new JTextField();
		txtViTri.setBounds(163, 92, 127, 19);
		contentPane.add(txtViTri);
		txtViTri.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Vị trí:");
		lblNewLabel_1.setBounds(51, 95, 66, 13);
		contentPane.add(lblNewLabel_1);
		
		btnXacNhan = new JButton("Xác nhận");
		
		btnXacNhan.setBounds(172, 231, 101, 21);
		contentPane.add(btnXacNhan);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setBounds(51, 262, 45, 19);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Chi tiết cuộc hẹn");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(201, 9, 147, 33);
		contentPane.add(lblNewLabel_2);
		ButtonGroup btngroup = new ButtonGroup();
		
		JLabel lblNewLabel_3 = new JLabel("Kiểu cuộc họp:");
		lblNewLabel_3.setBounds(51, 138, 85, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Số người tối đa:");
		lblNewLabel_4.setBounds(51, 178, 101, 13);
		contentPane.add(lblNewLabel_4);
		
		 cbbToiDa = new JComboBox();
		cbbToiDa.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		cbbToiDa.setBounds(162, 174, 45, 21);
		contentPane.add(cbbToiDa);
		
		ButtonGroup btnGroup = new ButtonGroup();
		rdbtnDon = new JRadioButton("Đơn");
		
		//rdbtnNhom.setSelected(true);
		rdbtnDon.setBounds(163, 134, 50, 21);
		
		
		rdbtnNhom = new JRadioButton("Nhóm");
		rdbtnNhom.setSelected(true);
		rdbtnNhom.setBounds(268, 134, 66, 21);
		
		btnGroup.add(rdbtnDon);
		btnGroup.add(rdbtnNhom);
		
		contentPane.add(rdbtnDon);
		contentPane.add(rdbtnNhom);
		

	}
	private Boolean checkHopLe()
	{
		Boolean status = true;
		if(txtTenSuKien.getText().equals(""))
			{status =false;}
		if(txtViTri.getText().equals(""))
			{status = false;}
		
		return status;
	}
	private void AddControls()
	{
		rdbtnDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbToiDa.setEnabled(false);
			}});
		rdbtnNhom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbToiDa.setEnabled(true);
			}});
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!checkHopLe())
				{
					JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);

				}
				else {
					dispose();
					ThongTin tt = new ThongTin();
					tt.ShowWindow();
				}
			}
		});
	}
	public void ShowWindow()
	{
		AddControls();
		this.setVisible(true);
		this.setSize(480, 300);
		this.setLocationRelativeTo(null);
		
		
	}
}
