package forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Customer;
import classes.Manager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserName.setForeground(new Color(255, 255, 255));
		lblUserName.setBounds(28, 69, 98, 37);
		contentPane.add(lblUserName);
		
		username = new JTextField();
		username.setBounds(151, 79, 96, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(31, 143, 95, 30);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setBounds(151, 150, 96, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(101, 22, 200, 20);
		comboBox.addItem("Customer");
		comboBox.addItem("Manger");
		contentPane.add(comboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				   if(comboBox.getSelectedItem()=="Customer")
				   {
                    Customer customer=new Customer();
                    if(customer.login(username.getText(), password.getText())==true)
                    {
                    	new Cart().setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null,"Wrong Username or Password");
				   }
				   else
				   {
					   Manager manger=new Manager();
					   if(manger.login(username.getText(), password.getText())==true)
	                    {
						   JOptionPane.showMessageDialog(null,"Welcome");
						   //Manger Form open
	                    }
					   else JOptionPane.showMessageDialog(null,"Wrong Username or Password");
				   }
			}
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setForeground(new Color(70, 130, 180));
		btnLogin.setBounds(280, 213, 115, 37);
		contentPane.add(btnLogin);
		
		
		
		JLabel lblLoginAs = new JLabel("Login as");
		lblLoginAs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoginAs.setForeground(Color.WHITE);
		lblLoginAs.setBackground(Color.WHITE);
		lblLoginAs.setBounds(28, 14, 82, 33);
		contentPane.add(lblLoginAs);
	}
}
