package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Card;
import classes.Customer;
import classes.Product;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class pay_in_Card extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField number;
	private JTextField CVV;
	private JTextField date;

	Customer customer;
	ArrayList<String> products;
	
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
		initialize();
	}
	
	public pay_in_Card(Customer cus, ArrayList<String> products) {
		this.customer = cus;
		this.products = products;
		initialize();
	}
	
	public void initialize() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Card card=new Card(name.getText(),number.getText(),CVV.getText(),date.getText());
				card.insertCard(card);
				
				customer.BuyProduct(products, LocalDate.now().toString());
				products.clear();
				
				JOptionPane.showMessageDialog(null, "Done Successfully");
				
				pay_in_Card frame = new pay_in_Card();
				frame.setVisible(false);
			
				
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDone.setBounds(327, 225, 97, 25);
		contentPane.add(btnDone);

		name = new JTextField();
		name.setBounds(156, 34, 153, 22);
		contentPane.add(name);
		name.setColumns(10);

		number = new JTextField();
		number.setBounds(156, 82, 153, 22);
		contentPane.add(number);
		number.setColumns(10);

		CVV = new JTextField();
		CVV.setBounds(156, 131, 153, 22);
		contentPane.add(CVV);
		CVV.setColumns(10);

		date = new JTextField();
		date.setBounds(156, 180, 153, 22);
		contentPane.add(date);
		date.setColumns(10);

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
		lblNewLabel_1.setBounds(28, 181, 117, 16);
		contentPane.add(lblNewLabel_1);
	}
}
