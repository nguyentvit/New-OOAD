package View;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Invite extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public Invite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 69, 77, 28);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(148, 48, 134, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(37, 41, 77, 28);
		contentPane.add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(148, 76, 134, 19);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			private Component panel;

			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(
                        panel, 
                        "Do you want to add another people?", 
                        "Alert", 
                        JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.NO_OPTION)
                dispose();
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(148, 124, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Invite People");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(132, 10, 122, 21);
		contentPane.add(lblNewLabel_1_1);
	}
}
