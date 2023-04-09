package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame{
	JButton btnXacNhan;
	JCalendar calendar;
	JComboBox cbbTimeStart,cbbTimeEnd;
	public View() {
		getContentPane().setLayout(null);
		
		btnXacNhan = new JButton("Xác nhận");
		
		
		btnXacNhan.setBounds(245, 270, 89, 23);
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
				if(timeStart > timeEnd)
				{
					JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					View1 view1 = new View1();
					view1.setVisible(true);
					view1.ShowWindow();
				}
				
			}
		});
	}
	public void ShowWindow()
	{
		
		this.setVisible(true);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		
	}
}
