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
	JButton btnXacNhan;
	JCalendar calendar;
	JComboBox cbbTimeStart,cbbTimeEnd;
	Connection con = null;
	Statement statement = null;
	ResultSet result = null;
	public View() {
		getContentPane().setLayout(null);
		
		btnXacNhan = new JButton("Xác nhận");
		
		
		btnXacNhan.setBounds(242, 269, 89, 23);
		getContentPane().add(btnXacNhan);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 10, 272, 202);
		getContentPane().add(calendar);
		
		cbbTimeStart = new JComboBox();
		cbbTimeStart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		cbbTimeStart.setBounds(433, 10, 82, 21);
		getContentPane().add(cbbTimeStart);
		
		 cbbTimeEnd = new JComboBox();
		cbbTimeEnd.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		cbbTimeEnd.setBounds(433, 55, 82, 21);
		getContentPane().add(cbbTimeEnd);
		
		JLabel lblNewLabel = new JLabel("Thời gian bắt đầu:");
		lblNewLabel.setBounds(311, 14, 110, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thời gian kết thúc:");
		lblNewLabel_1.setBounds(311, 59, 112, 13);
		getContentPane().add(lblNewLabel_1);
		AddEvents();
		
		
	}
	
	public void AddEvents()
	{
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int timeStart = Integer.parseInt(cbbTimeStart.getSelectedItem().toString());
				int timeEnd = Integer.parseInt(cbbTimeEnd.getSelectedItem().toString());
				if(timeStart >= timeEnd)
				{	System.out.print(check1());
					JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					
				} else
					try {
						if (!check()) {
							
						}
						else {
							dispose();
							Appt appt = new Appt();
							
							
							appt.ShowWindow();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
			private String check1()
			{
				Boolean status = true;
				
				Date date = calendar.getDate();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
				String date1 = dateFormat.format(date);
				
				return date1;
			}
			private boolean check() throws SQLException {
				
					Boolean status = true;
					con = ConnectionDB.getConnect();
					statement = con.createStatement();
					String query = "select NgayDienRa,TgBatDau,TgKetThuc from Lich";
					result = statement.executeQuery(query);
					//String NgayDienRa,TgBatDau,TgKetThuc;
					List<Ngay> myList=  new ArrayList<Ngay>();
					while(result.next())
					{
						Ngay i = new Ngay();
						i.NgayDienRa = result.getString("NgayDienRa");
						i.TgBatDau = result.getString("TgBatDau");
						i.TgKetThuc = result.getString("TgKetThuc");
						myList.add(i);
					}
					for(Ngay i : myList)
					{
						
					}
					
					return status;
				
				
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
