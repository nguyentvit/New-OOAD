package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import KetNoiCSDL.ConnectionDB;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ChiTiet extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String id;
	private DefaultTableModel dtm;
	private JLabel lblTenSuKien,lblTgBatDau,lblTgKetThuc,lblNgayDienRa,lblViTri;
	public ChiTiet(String k) {
		id = k;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin chi tiết");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(192, 11, 190, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên sự kiện:");
		lblNewLabel_1.setBounds(35, 68, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		lblTenSuKien = new JLabel("Hello kitty");
		lblTenSuKien.setBounds(117, 68, 129, 14);
		contentPane.add(lblTenSuKien);
		
		JLabel lblNewLabel_3 = new JLabel("Ngày diễn ra:");
		lblNewLabel_3.setBounds(35, 127, 100, 14);
		contentPane.add(lblNewLabel_3);
		
		 lblNgayDienRa = new JLabel("2222");
		lblNgayDienRa.setBounds(117, 127, 129, 14);
		contentPane.add(lblNgayDienRa);
		
		JLabel lblNewLabel_5 = new JLabel("Thời gian bắt đầu:");
		lblNewLabel_5.setBounds(280, 68, 129, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Thời gian kết thúc:");
		lblNewLabel_6.setBounds(280, 100, 129, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Thông tin người tham gia:");
		lblNewLabel_7.setBounds(35, 152, 158, 14);
		contentPane.add(lblNewLabel_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 177, 441, 141);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"STT", "ID", "Họ và tên", "Sđt", "Giới tính"
				}
			);
		table.setModel(dtm);
		
		 lblTgBatDau = new JLabel("1");
		lblTgBatDau.setBounds(409, 68, 46, 14);
		contentPane.add(lblTgBatDau);
		
		 lblTgKetThuc = new JLabel("2");
		lblTgKetThuc.setBounds(409, 100, 46, 14);
		contentPane.add(lblTgKetThuc);
		
		JLabel lblNewLabel_2 = new JLabel("Vị trí:");
		lblNewLabel_2.setBounds(35, 100, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		 lblViTri = new JLabel("New label");
		lblViTri.setBounds(115, 102, 46, 14);
		contentPane.add(lblViTri);
		
		
	}
	private void HienThiTT() throws SQLException
	{
		Connection con = ConnectionDB.getConnect();
		Statement statement = con.createStatement();
		Statement statement1 = con.createStatement();
		String query = "select ID_Nguoi,Ten,Sdt,Gender from Lich inner join Nguoi on Lich.Id_Lich = Nguoi.Id_Lich where Lich.Id_Lich = " + id;
		ResultSet result = statement.executeQuery(query);
		String query1 = "select TenSuKien,ViTri,NgayDienRa,TgBatDau,TgKetThuc from Lich where Id_Lich = " + id;
		ResultSet result1 = statement1.executeQuery(query1);
		while(result1.next())
		{
			lblTenSuKien.setText(result1.getString("TenSuKien"));
			lblNgayDienRa.setText(result1.getString("NgayDienRa"));
			lblViTri.setText(result1.getString("ViTri"));
			lblTgBatDau.setText(result1.getString("TgBatDau"));
			lblTgKetThuc.setText(result1.getString("TgKetThuc"));
			
		}
		int i = 0;
		while(result.next())
		{
			
			i++;
			Vector<Object> vec = new Vector<Object>();
			vec.add(i);
			vec.add(result.getString("Id_Nguoi"));
			vec.add(result.getString("Ten"));
			vec.add(result.getString("Sdt"));
			String gt = (result.getString("Gender").equals("0"))?"Nam":"Nu";
			vec.add(gt);
			dtm.addRow(vec);
			
		}
	}
	public void ShowWindow() throws SQLException
	{	
		HienThiTT();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}
