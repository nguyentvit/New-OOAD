package View;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
	private JButton btnThem;
	private DefaultTableModel dtmLich;
	Connection con = null;
	Statement statement = null;
	ResultSet result = null;
	JButton btnChiTiet;

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
		
		btnThem.setBounds(69, 311, 89, 23);
		getContentPane().add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(213, 311, 89, 23);
		getContentPane().add(btnXoa);
		
		JButton btnThayDoi = new JButton("Thay đổi");
		btnThayDoi.setBounds(362, 311, 89, 23);
		getContentPane().add(btnThayDoi);
		
		btnChiTiet = new JButton("Chi tiết");
		
		
		btnChiTiet.setBounds(507, 311, 89, 23);
		getContentPane().add(btnChiTiet);

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
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				int value = table.getSelectedRow();
				String id = table.getModel().getValueAt(0, 1).toString();
				ChiTiet ui = new ChiTiet(id);
				
				try {
					ui.ShowWindow();
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
