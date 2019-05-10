package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pay_in_Card extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pay_in_Card frame = new pay_in_Card();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pay_in_Card() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Done Successfully");
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDone.setBounds(308, 215, 97, 25);
		contentPane.add(btnDone);

		textField = new JTextField();
		textField.setBounds(156, 34, 153, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(156, 82, 153, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(156, 131, 153, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(156, 180, 153, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblCardNumber = new JLabel("Name on Card");
		lblCardNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCardNumber.setBounds(28, 37, 131, 16);
		contentPane.add(lblCardNumber);

		JLabel lblCvv = new JLabel("Number");
		lblCvv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCvv.setBounds(39, 85, 79, 16);
		contentPane.add(lblCvv);

		JLabel lblNewLabel = new JLabel("CVV");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(39, 134, 56, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Expiration Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(28, 182, 117, 16);
		contentPane.add(lblNewLabel_1);
	}
}
