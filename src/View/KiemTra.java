package View;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DTO.Ngay;
import KetNoiCSDL.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;

public class KiemTra extends JFrame {
	private JTable table;
	private JButton btnThem,btnXoa;
	private DefaultTableModel dtmLich;
	Connection con = null;
	Statement statement = null;
	ResultSet result = null;
	JButton btnChiTiet;
	JButton btnThemThanhVien;
	private JButton btnThayDoi;

	public KiemTra() {
		setTitle("Lịch của bạn");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 783, 237);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		dtmLich = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"STT","Mã sự kiện", "Tên sự kiện", "Vị Trí", "Ngày diễn ra", "Bắt đầu", "Kết thúc","Kiểu nhóm","Số người tối đa"
				}
				
			);
		
		table.setModel(dtmLich);
		table.setDefaultEditor(Object.class, null);
		
		JLabel lblNewLabel = new JLabel("Lịch của bạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(313, 11, 203, 24);
		getContentPane().add(lblNewLabel);
		
		 btnThem = new JButton("Thêm");
		
		btnThem.setBounds(46, 311, 117, 23);
		getContentPane().add(btnThem);
		
		 btnXoa = new JButton("Xóa");
		
		btnXoa.setBounds(194, 311, 117, 23);
		getContentPane().add(btnXoa);
		
		 btnThemThanhVien = new JButton("Thêm người");
		btnThemThanhVien.setBounds(338, 311, 117, 23);
		getContentPane().add(btnThemThanhVien);
		
		btnChiTiet = new JButton("Chi tiết");
		
		
		btnChiTiet.setBounds(487, 311, 117, 23);
		getContentPane().add(btnChiTiet);
		
		btnThayDoi = new JButton("Thay đổi");
		
		btnThayDoi.setBounds(633, 311, 117, 23);
		getContentPane().add(btnThayDoi);

	}
	
	private void HienThiThongTien() throws SQLException
	{
		dtmLich.setRowCount(0);
		String query = "select * from Lich";
		con = ConnectionDB.getConnect();
		statement = con.createStatement();
		result = statement.executeQuery(query);
		int i = 0;
		while(result.next())
		{
			Vector<Object> vec = new Vector<Object>();
			++i;
			vec.add(i);
			vec.add(result.getString("Id_Lich"));
			String KieuNhom = result.getString("KieuNhom");
			if(KieuNhom.equals("0")) {
				KieuNhom = "Đơn";
			}
			else {
				KieuNhom = "Nhóm";
			}
			vec.add(result.getString("TenSuKien"));
			vec.add(result.getString("ViTri"));
			vec.add(result.getString("NgayDienRa"));
			vec.add(result.getString("TgBatDau"));
			vec.add(result.getString("TgKetThuc"));
			vec.add(KieuNhom);
			vec.add(result.getString("ToiDa"));
			dtmLich.addRow(vec);
		}
		
	}
	private void AddEvents()
	{
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				View ui = new View();
				ui.ShowWindow();
			}
		});
		btnThemThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = table.getSelectedRow();
				String id = table.getModel().getValueAt(value, 1).toString();
				try {
					int SoNguoi = 0;
					statement = con.createStatement();
					String query = "select count(*) as 'SoNguoi' from Nguoi where Id_Lich = "+id;
					result = statement.executeQuery(query);
					while(result.next())
					{
						SoNguoi = Integer.parseInt(result.getString("SoNguoi"));
					}
					int ToiDa = Integer.parseInt(table.getModel().getValueAt(value, 8).toString());
					if(SoNguoi == ToiDa)
					{
						JOptionPane.showMessageDialog(null,"Số người đã tối đa","Thông báo",JOptionPane.OK_OPTION);
					}
					if(SoNguoi < ToiDa)
					{
						
						ThongTin tt = new ThongTin(id);
						tt.ShowWindow();
						
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				int value = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(value, 1).toString();
				ChiTiet ui = new ChiTiet(id);
				
				try {
					ui.ShowWindow();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					con = ConnectionDB.getConnect();
					int value = table.getSelectedRow();
					String id = table.getModel().getValueAt(value, 1).toString();
					statement = con.createStatement();
					String query1 = "delete from Nguoi where Id_Lich = " + id;
					statement.executeUpdate(query1);
					
					statement = con.createStatement();
					String query2 = "delete from Lich where Id_Lich = " + id;
					statement.executeUpdate(query2);
					JOptionPane.showMessageDialog(null,"Xóa lịch thành công","Thông báo",JOptionPane.OK_OPTION);
					HienThiThongTien();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThayDoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int value = table.getSelectedRow();
					String id = table.getModel().getValueAt(value, 1).toString();
					Appt at = new Appt(null,id,false);
					at.ShowWindow();
					HienThiThongTien();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public void ShowWindow() throws SQLException
	{
		AddEvents();
		HienThiThongTien();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(820,435);
		
	}
}
