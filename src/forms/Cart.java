package forms;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Category;
import classes.Customer;
import classes.MenuComponent;
import classes.Order;
import classes.Product;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Cart extends JFrame {

	private JPanel contentPane;
    private Customer customer;
    private Order order;
    private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame = new Cart();
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
	public Cart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton.setBounds(316, 243, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                DefaultTableModel defaultTableModel = new DefaultTableModel();
				
				defaultTableModel.addColumn("Name");
				defaultTableModel.addColumn("Description");
				defaultTableModel.addColumn("Price");
				
				List<Product> products=order.getProducts();
				
				for(int i = 0; i < products.size(); i++)
				{
					 System.out.println(products.get(i).getName());
					defaultTableModel.addRow(new Object[]
					{
						products.get(i).getName(),products.get(i).getDescription(),products.get(i).getPrice()	
					});
				}
				table.setModel(defaultTableModel);
				
			}
		});
		btnViewCart.setBounds(167, 243, 117, 29);
		contentPane.add(btnViewCart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 53, 336, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
