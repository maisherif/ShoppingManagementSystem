package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Box;
import java.awt.Component;

import classes.*;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Main {

	public JFrame frame;
	private JTable productsTable;
	private static Customer customer;
	ArrayList<Product> products;
	private ArrayList<String> cart = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main(customer);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main(Customer cus) {
		customer = cus;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JLabel lblCustomerName = new JLabel("CustomerName");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {				
				lblCustomerName.setText("Hello, " + customer.GetName() + "  ");
				
				products = customer.getProducts();

				DefaultTableModel defaultTableModel = new DefaultTableModel();

				defaultTableModel.addColumn("Name");
				defaultTableModel.addColumn("Description");
				defaultTableModel.addColumn("Price");
				defaultTableModel.addColumn("Number in Stock");

				for(Product menuComponent : products) {
					defaultTableModel.addRow(new Object[] {
							menuComponent.getName(), menuComponent.getDescription(),
							menuComponent.getPrice(), menuComponent.getQuantity()
					});
				}
				productsTable.setModel(defaultTableModel);
			}
		});
		frame.setBounds(100, 100, 786, 437);

		Box horizontalBox = Box.createHorizontalBox();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);

		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new Cart(customer, cart).setVisible(true);
			}
		});

		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(productsTable.getSelectedRow() != -1) {
					cart.add(products.get(productsTable.getSelectedRow()).getName());
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select a product to add to the cart.");
				}
			}
		});
		
		JButton btnViewBill = new JButton("View Bill");
		btnViewBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CustomerBills(customer).frame.setVisible(true);
			}
		});
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.deleteCustomer(customer);
				Main.this.frame.setVisible(false);
				new Login().setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(horizontalBox, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDeleteAccount, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAddToCart, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnViewCart, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
									.addComponent(btnViewBill, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnViewBill)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddToCart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewCart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeleteAccount))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
					.addContainerGap())
		);
		
				JButton btnAllCategories = new JButton("All Categories");
				btnAllCategories.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						products = customer.getProducts();

						DefaultTableModel defaultTableModel = new DefaultTableModel();

						defaultTableModel.addColumn("Name");
						defaultTableModel.addColumn("Description");
						defaultTableModel.addColumn("Price");
						defaultTableModel.addColumn("Number in Stock");

						for(Product menuComponent : products) {
							defaultTableModel.addRow(new Object[] {
									menuComponent.getName(), menuComponent.getDescription(),
									menuComponent.getPrice(), menuComponent.getQuantity()
							});
						}
						productsTable.setModel(defaultTableModel);
					}
				});
				horizontalBox.add(btnAllCategories);
		
				JButton btnClothes = new JButton("Clothes");
				btnClothes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						products = customer.getCategoryProducts("Clothes");


						DefaultTableModel defaultTableModel = new DefaultTableModel();

						defaultTableModel.addColumn("Name");
						defaultTableModel.addColumn("Description");
						defaultTableModel.addColumn("Price");
						defaultTableModel.addColumn("Number in Stock");

						for(Product menuComponent : products) {
							defaultTableModel.addRow(new Object[] {
									menuComponent.getName(), menuComponent.getDescription(),
									menuComponent.getPrice(), menuComponent.getQuantity()
							});
						}
						productsTable.setModel(defaultTableModel);
					}
				});
				horizontalBox.add(btnClothes);
		
				JButton btnPhones = new JButton("Phones");
				btnPhones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						products = customer.getCategoryProducts("Phones");


						DefaultTableModel defaultTableModel = new DefaultTableModel();

						defaultTableModel.addColumn("Name");
						defaultTableModel.addColumn("Description");
						defaultTableModel.addColumn("Price");
						defaultTableModel.addColumn("Number in Stock");

						for(Product menuComponent : products) {
							defaultTableModel.addRow(new Object[] {
									menuComponent.getName(), menuComponent.getDescription(),
									menuComponent.getPrice(), menuComponent.getQuantity()
							});
						}
						productsTable.setModel(defaultTableModel);
					}
				});
				horizontalBox.add(btnPhones);
		
				JButton btnElectronics = new JButton("Electronics");
				btnElectronics.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						products = customer.getCategoryProducts("Electronics");


						DefaultTableModel defaultTableModel = new DefaultTableModel();

						defaultTableModel.addColumn("Name");
						defaultTableModel.addColumn("Description");
						defaultTableModel.addColumn("Price");
						defaultTableModel.addColumn("Number in Stock");

						for(Product menuComponent : products) {
							defaultTableModel.addRow(new Object[] {
									menuComponent.getName(), menuComponent.getDescription(),
									menuComponent.getPrice(), menuComponent.getQuantity()
							});
						}
						productsTable.setModel(defaultTableModel);
					}
				});
				horizontalBox.add(btnElectronics);
		
				JButton btnComputing = new JButton("Computing");
				btnComputing.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						products = customer.getCategoryProducts("Computing");


						DefaultTableModel defaultTableModel = new DefaultTableModel();

						defaultTableModel.addColumn("Name");
						defaultTableModel.addColumn("Description");
						defaultTableModel.addColumn("Price");
						defaultTableModel.addColumn("Number in Stock");

						for(Product menuComponent : products) {
							defaultTableModel.addRow(new Object[] {
									menuComponent.getName(), menuComponent.getDescription(),
									menuComponent.getPrice(), menuComponent.getQuantity()
							});
						}
						productsTable.setModel(defaultTableModel);
					}
				});
				horizontalBox.add(btnComputing);

		Component verticalStrut = Box.createVerticalStrut(20);
		horizontalBox.add(verticalStrut);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);				
				Main.this.frame.setVisible(false);
			}
		});
		
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox.add(lblCustomerName);
		horizontalBox.add(btnLogout);

		productsTable = new JTable();
		productsTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{

			}
		});
		productsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		productsTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				}
				));
		productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(productsTable);
		frame.getContentPane().setLayout(groupLayout);
	}
}
