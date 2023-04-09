package View;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class KiemTra extends JFrame {
	private JTable table;

	private DefaultTableModel dtmLich;

	public KiemTra() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 700, 295);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		dtmLich = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"STT", "Tên sự kiện", "Vị Trí", "Ngày diễn ra", "Bắt đầu", "Kết thúc","Kiểu nhóm","Số người tối đa"
				}
			);
		table.setModel(dtmLich);

	}
	
	private void HienThiThongTien()
	{
		dtmLich.setRowCount(0);
		Vector<Object> vec = new Vector<Object>();
		vec.add("1");
		vec.add("1");
		vec.add("1");
		vec.add("1");
		vec.add("1");
		vec.add("1");
		vec.add("1");
		vec.add("1");
		dtmLich.addRow(vec);
	}
	public void ShowWindow()
	{
		HienThiThongTien();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(760,400);
		
	}
}
