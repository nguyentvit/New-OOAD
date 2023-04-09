package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import java.awt.Font;
import java.awt.FlowLayout;

public class Calendar extends JFrame {

	private JPanel contentPane;

	public Calendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Calendar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(184, 10, 73, 41);
		contentPane.add(lblNewLabel);
		
		JDayChooser dayChooser = new JDayChooser();
		dayChooser.setBounds(98, 61, 189, 133);
		contentPane.add(dayChooser);
		dayChooser.getDayPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	public void ShowWindow()
	{
		this.setVisible(true);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		
	}
}
