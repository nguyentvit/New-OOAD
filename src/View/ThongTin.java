package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongTin extends JFrame{
	private JTextField txtTen;
	private JTextField txtSdt;
	private JTextField txtTuoi;
	JRadioButton rdbtnNam,rdbtnNu;
	JButton btnXacNhan;
	public ThongTin() {
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
		
		JLabel lblNewLabel_4 = new JLabel("Tuổi:");
		lblNewLabel_4.setBounds(10, 185, 77, 13);
		getContentPane().add(lblNewLabel_4);
		
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
		
		txtTuoi = new JTextField();
		txtTuoi.setBounds(97, 182, 37, 19);
		getContentPane().add(txtTuoi);
		txtTuoi.setColumns(10);
		
		 btnXacNhan = new JButton("Xác nhận");
		
		btnXacNhan.setBounds(189, 232, 96, 21);
		getContentPane().add(btnXacNhan);
		
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
		if(txtTuoi.getText().equals(""))
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
					try{
			            int Tuoi = Integer.parseInt(txtTuoi.getText());
			            
			            
			        }
			        catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "Tuổi không hợp lệ","Thông báo",JOptionPane.INFORMATION_MESSAGE);

			        	//ex.getMessage();
			            //ex.printStackTrace();
			        }
				}
			}
		});
	}
	public void ShowWindow()
	{
		AddControls();
		this.setVisible(true);
		this.setSize(450,300);
		this.setLocationRelativeTo(null);
		
	}
}
