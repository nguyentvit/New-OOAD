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

import DTO.Ngay;
import KetNoiCSDL.ConnectionDB;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JRadioButton;

public class Appt extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenSuKien;
	private JTextField txtViTri;
	JButton btnXacNhan;
	JRadioButton rdbtnDon,rdbtnNhom;
	JComboBox cbbToiDa;
	private Ngay date;
	Connection con = null;
	Statement statement = null;
	ResultSet result = null;
	private Boolean them;
	private String idl;
	public Appt(Ngay i,String idl,Boolean them) {
		this.idl = idl;
		this.them = them;
		date = i;
		setTitle("Thêm cuộc hẹn");
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
	private String RandomId() throws SQLException
	{
		
		String Id = "";
		Boolean status = true;
		con = ConnectionDB.getConnect();
		statement = con.createStatement();
		String query = "select Id_Lich from Lich";
		result = statement.executeQuery(query);
		List<String> myList = new ArrayList<String>();
		while(status)
		{
			status = false;
			Random rand = new Random();
			Id = Integer.toString(rand.nextInt((100-0)+1)+0);
			for(String i : myList)
			{
				if(i.equals(Id))status=true;
			}
		}
		
		return Id;
		
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
				if(them == true)
				{
					if(!checkHopLe())
					{
						JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);

					}
					else {
						String id1 = "";
						//JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
						try {
							con = ConnectionDB.getConnect();
							statement = con.createStatement();
							String id = RandomId();
							id1 = id;
							String TenSuKien = txtTenSuKien.getText();
							String ViTri = txtViTri.getText();
							String KieuNhom = (rdbtnDon.isSelected())?"0":"1";
							String ToiDa = (rdbtnDon.isSelected())?"1":cbbToiDa.getSelectedItem().toString();
							String NgayDienRa = date.NgayDienRa;
							String TgBatDau = Integer.toString(date.TgBatDau);
							String TgKetThuc = Integer.toString(date.TgKetThuc);
							String query = String.format("insert into Lich values ('%s',N'%s',N'%s','%s','%s','%s','%s','%s')",id,TenSuKien,ViTri,NgayDienRa,TgBatDau,TgKetThuc,KieuNhom,ToiDa );
							statement.executeUpdate(query);
							int option = 0;
							option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm thành viên không","Thông báo",JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.OK_OPTION)
							{
								dispose();
								ThongTin tt = new ThongTin(id1);
								tt.ShowWindow();
							}
							else {
								dispose();
								KiemTra kt = new KiemTra();
								try {
									kt.ShowWindow();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						
					}
				}
				else {
					if(!checkHopLe())
					{
						JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);

					}
					else {
						try {
							con = ConnectionDB.getConnect();
							statement = con.createStatement();
							String query = "select count(*) as SoNguoi from Nguoi where Id_Lich = " + idl;
							result = statement.executeQuery(query);
							int SoNguoi = 0;
							while(result.next())
							{
								SoNguoi = Integer.parseInt(result.getString("SoNguoi"));
							}
							if(Integer.parseInt(cbbToiDa.getSelectedItem().toString()) < SoNguoi)
							{
								JOptionPane.showMessageDialog(null, "Số người thay đổi ít hơn số người hiện có","Thông báo",JOptionPane.INFORMATION_MESSAGE);

							}
							else {
								String ten = txtTenSuKien.getText();
								String vitri = txtViTri.getText();
								String kieunhom = (rdbtnDon.isSelected())?"0":"1";
								String toida = cbbToiDa.getSelectedItem().toString();
								statement = con.createStatement();
								String query1 = String.format("update Lich set TenSuKien = N'%s',ViTri = N'%s',KieuNhom = '%s',ToiDa = '%s' where Id_Lich = %s",ten,vitri,kieunhom,toida,idl);
								statement.executeUpdate(query1);
								dispose();
							}
							
						
							
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
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
