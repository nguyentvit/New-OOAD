package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import KetNoiCSDL.ConnectionDB;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class ThongTin extends JFrame{
	private JTextField txtTen;
	private JTextField txtSdt;
	JRadioButton rdbtnNam,rdbtnNu;
	JButton btnXacNhan;
	private String idLich;
	private Connection con = null;
	private Statement statement = null;
	private ResultSet result = null;
	public ThongTin(String id) {
		idLich = id;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin người đặt");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(150, 10, 177, 26);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ và tên:");
		lblNewLabel_1.setBounds(10, 51, 77, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sđt:");
		lblNewLabel_2.setBounds(10, 90, 77, 13);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giới tính:");
		lblNewLabel_3.setBounds(10, 137, 77, 13);
		getContentPane().add(lblNewLabel_3);
		
		txtTen = new JTextField();
		txtTen.setBounds(97, 48, 96, 19);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);
		
		txtSdt = new JTextField();
		txtSdt.setBounds(97, 87, 96, 19);
		getContentPane().add(txtSdt);
		txtSdt.setColumns(10);
		ButtonGroup buttongroup = new ButtonGroup();
		 rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(93, 133, 63, 21);
		getContentPane().add(rdbtnNam);
		
		 rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(198, 133, 50, 21);
		
		buttongroup.add(rdbtnNam);rdbtnNam.setSelected(true);
		buttongroup.add(rdbtnNu);
		
		getContentPane().add(rdbtnNam);
		getContentPane().add(rdbtnNu);
		
		 btnXacNhan = new JButton("Xác nhận");
		
		
		btnXacNhan.setBounds(185, 179, 96, 21);
		getContentPane().add(btnXacNhan);
		
	}
	private String RandomId() throws SQLException
	{
		
		String Id = "";
		Boolean status = true;
		con = ConnectionDB.getConnect();
		statement = con.createStatement();
		String query = "select Id_Nguoi from Nguoi";
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
		if(txtTen.getText().equals(""))
		{
			status = false;
		}
		if(txtSdt.getText().equals(""))
		{
			status = false;
		}
		
		
		
		return status;
		
	}
	private void AddControls()
	{
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkHopLe())
				{
					JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);

				}
				else {
					
			           
			            try {
							con = ConnectionDB.getConnect();
							statement = con.createStatement();
							String ten = txtTen.getText();
					        String sdt = txtSdt.getText();
					        String gt = (rdbtnNam.isSelected())?"0":"1";
					        String id = RandomId();
					        
					        String query = String.format("insert into Nguoi values ('%s','%s',N'%s','%s','%s')",id,idLich,ten,sdt,gt);
					        statement.executeUpdate(query);
					        dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			            
			            
			            
			            
			        
				}
			}
		});
	}
	public void ShowWindow()
	{
		AddControls();
		this.setVisible(true);
		this.setSize(450,250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
}
