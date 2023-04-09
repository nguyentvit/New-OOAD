package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;

public class Appt extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public Appt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Event Name");
		lblNewLabel.setBounds(51, 141, 66, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(127, 138, 127, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 175, 127, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Location ");
		lblNewLabel_1.setBounds(51, 178, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox.setBounds(331, 260, 113, 21);
		contentPane.add(comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("All Day");
		chckbxNewCheckBox.setBounds(367, 137, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		//float [] items = { "12.00", "12.", "Third item", "Fourth item" };
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_1.setBounds(331, 223, 113, 21);
		contentPane.add(comboBox_1);
		//String [] items = { "First item", "Second item", "Third item", "Fourth item" };

		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(127, 223, 127, 19);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(127, 262, 127, 19);
		contentPane.add(dateChooser_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(261, 321, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("End");
		lblNewLabel_1_1.setBounds(51, 262, 31, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setBounds(51, 262, 45, 13);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Start ");
		lblNewLabel_1_1_1.setBounds(51, 227, 52, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("DETAIL EVENT");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(214, 57, 113, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnInvitePeople = new JButton("Invite people");
		btnInvitePeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.out.println("TEST");
			       Invite window2 = new Invite();
			       window2.setVisible(true);
			       //getContentPane().add(btnInvitePeople, BorderLayout.CENTER);
			       //pack();
			}
			
		});
		btnInvitePeople.setBounds(359, 165, 85, 39);
		contentPane.add(btnInvitePeople);
		

	}
	public void ShowWindow()
	{
		this.setVisible(true);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		
	}
}
