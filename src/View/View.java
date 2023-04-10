package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

import DTO.Ngay;
import KetNoiCSDL.ConnectionDB;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class View extends JFrame{
	private JButton btnXacNhan;
	private JCalendar calendar;
	private JComboBox cbbTimeStart,cbbTimeEnd;
	private Connection con = null;
	private Statement statement = null;
	private ResultSet result = null;
	private JButton btnTroVe;
	private Boolean thaydoi;
	private String idl;
	public View(String idl,Boolean thaydoi) {
		this.idl = idl;
		this.thaydoi = thaydoi;
		getContentPane().setLayout(null);
		
		btnXacNhan = new JButton("Xác nhận");
		
		
		btnXacNhan.setBounds(215, 269, 89, 23);
		getContentPane().add(btnXacNhan);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 36, 272, 202);
		getContentPane().add(calendar);
		
		cbbTimeStart = new JComboBox();
		cbbTimeStart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		cbbTimeStart.setBounds(429, 32, 82, 21);
		getContentPane().add(cbbTimeStart);
		
		 cbbTimeEnd = new JComboBox();
		cbbTimeEnd.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		cbbTimeEnd.setBounds(429, 68, 82, 21);
		getContentPane().add(cbbTimeEnd);
		
		JLabel lblNewLabel = new JLabel("Thời gian bắt đầu:");
		lblNewLabel.setBounds(309, 36, 110, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thời gian kết thúc:");
		lblNewLabel_1.setBounds(307, 73, 112, 13);
		getContentPane().add(lblNewLabel_1);
		
		btnTroVe = new JButton("Lịch Chi Tiết");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				KiemTra ui = new KiemTra();
				try {
					ui.ShowWindow();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTroVe.setBounds(10, 2, 119, 23);
		getContentPane().add(btnTroVe);
		AddEvents();
		
		
	}
	
	public void AddEvents()
	{
		if(thaydoi == true)
		{
			btnTroVe.setVisible(false);
		}
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thaydoi == false)
				{
					int timeStart = Integer.parseInt(cbbTimeStart.getSelectedItem().toString());
					int timeEnd = Integer.parseInt(cbbTimeEnd.getSelectedItem().toString());
					if(timeStart >= timeEnd)
					{	
						JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
						
					} else
						try {
							if (!check()) {
								JOptionPane.showMessageDialog(null, "Lịch của bạn đã bị trùng","Thông báo",JOptionPane.INFORMATION_MESSAGE);

							}
							else {
								dispose();
								Date date2 = calendar.getDate();
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
								String date1 = dateFormat.format(date2);
								Ngay i = new Ngay();
								i.NgayDienRa = date1;
								i.TgBatDau = Integer.parseInt(cbbTimeStart.getSelectedItem().toString());
								i.TgKetThuc = Integer.parseInt(cbbTimeEnd.getSelectedItem().toString());
								Appt appt = new Appt(i,null,true);
								appt.ShowWindow();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				else {
					int timeStart = Integer.parseInt(cbbTimeStart.getSelectedItem().toString());
					int timeEnd = Integer.parseInt(cbbTimeEnd.getSelectedItem().toString());
					if(timeStart >= timeEnd)
					{	
						JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
						
					} else
						try {
							if (!check()) {
								JOptionPane.showMessageDialog(null, "Lịch của bạn đã bị trùng","Thông báo",JOptionPane.INFORMATION_MESSAGE);

							}
							else {
								dispose();
								Date date2 = calendar.getDate();
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
								String date1 = dateFormat.format(date2);
								Ngay i = new Ngay();
								i.NgayDienRa = date1;
								i.TgBatDau = Integer.parseInt(cbbTimeStart.getSelectedItem().toString());
								i.TgKetThuc = Integer.parseInt(cbbTimeEnd.getSelectedItem().toString());
								con = ConnectionDB.getConnect();
								statement = con.createStatement();
								String query = String.format("update Lich set NgayDienRa = '%s',TgBatDau = '%s',TgKetThuc = '%s' where Id_Lich = %s",i.NgayDienRa,i.TgBatDau,i.TgKetThuc,idl);
								statement.executeUpdate(query);
								int result2 = JOptionPane.showConfirmDialog(null,"Bạn có muốn thay đổi thông tin chi tiết không","Thông báo",JOptionPane.YES_NO_OPTION);
								if(result2 == JOptionPane.YES_OPTION)
								{
									dispose();
									Appt appt = new Appt(null,idl,false);
									appt.ShowWindow();
								}
								else {
									dispose();
									KiemTra kt = new KiemTra();
									kt.ShowWindow();
								}
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
				
				
				}
				
				
			}
			private Boolean check1(String date,int tgBatDau,int tgKetThuc)
			{
				int i=0;
				Date date2 = calendar.getDate();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String date1 = dateFormat.format(date2);
				int tgStart = Integer.parseInt(cbbTimeStart.getSelectedItem().toString());
				int tgEnd = Integer.parseInt(cbbTimeEnd.getSelectedItem().toString());
				
				if(date1.equals(date))
				{
					if(tgStart >= tgBatDau && tgStart < tgKetThuc) {i++;}
					if(tgEnd > tgKetThuc && tgEnd <= tgKetThuc) {i++;}
					if(tgStart <= tgBatDau && tgEnd >= tgKetThuc) {
						i++;
					}
					if(i==0)return true;
					else return false;
				}
				else {
					return true;
				}
				
				
				
			}
			private boolean check() throws SQLException {
				
					
					con = ConnectionDB.getConnect();
					statement = con.createStatement();
					String query;
					if(thaydoi == false)
					{
						query = "select NgayDienRa,TgBatDau,TgKetThuc from Lich";
					}
					else {
						query = "select NgayDienRa,TgBatDau,TgKetThuc from Lich where Id_Lich != " + idl;
					}
					result = statement.executeQuery(query);
					//String NgayDienRa,TgBatDau,TgKetThuc;
					List<Ngay> myList=  new ArrayList<Ngay>();
					while(result.next())
					{ 
						Ngay i = new Ngay();
						i.NgayDienRa = result.getString("NgayDienRa");
						i.TgBatDau = Integer.parseInt(result.getString("TgBatDau"));
						i.TgKetThuc = Integer.parseInt(result.getString("TgKetThuc"));
						myList.add(i);
					}
					for(Ngay i : myList)
					{
						if(!check1(i.NgayDienRa,i.TgBatDau,i.TgKetThuc))
						{
							
							return false;
						}
					}
					
					return true;
				
				
			}

			
		});
	}
	public void ShowWindow()
	{
		
		this.setVisible(true);
		this.setSize(600, 360);
		this.setLocationRelativeTo(null);
		
	}
}
