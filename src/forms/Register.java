package forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Customer;
import classes.WebPage;

import javax.swing.JTextField;
import java.io.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JPasswordField password;
	private JTextField Mobile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBounds(115, 73, 188, 20);
		contentPane.add(name);
		name.setColumns(10);

		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username.setBounds(115, 108, 188, 20);
		contentPane.add(username);
		username.setColumns(10);

		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegister.setForeground(new Color(70, 130, 180));
		btnRegister.setBackground(new Color(255, 255, 255));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebPage onlineshopping = new WebPage();
				Customer customer=new Customer(name.getText(),Mobile.getText(),password.getText(),username.getText());
				onlineshopping.register(customer);
	
				Register.this.setVisible(false);
			}
		});
		btnRegister.setBounds(399, 263, 119, 38);
		contentPane.add(btnRegister);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(10, 69, 60, 29);
		contentPane.add(lblName);

		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBackground(new Color(255, 255, 255));
		lblUsername.setBounds(10, 104, 96, 29);
		contentPane.add(lblUsername);

		JLabel lblRedister = new JLabel("Register");
		lblRedister.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblRedister.setForeground(new Color(255, 255, 255));
		lblRedister.setBounds(10, 11, 96, 38);
		contentPane.add(lblRedister);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(10, 144, 86, 20);
		contentPane.add(lblPassword);

		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password.setBounds(115, 144, 188, 20);
		contentPane.add(password);

		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMobile.setForeground(new Color(255, 255, 255));
		lblMobile.setBackground(new Color(255, 255, 255));
		lblMobile.setBounds(10, 175, 60, 20);
		contentPane.add(lblMobile);

		Mobile = new JTextField();
		Mobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Mobile.setBounds(115, 175, 188, 20);
		contentPane.add(Mobile);
		Mobile.setColumns(10);
	}
}
