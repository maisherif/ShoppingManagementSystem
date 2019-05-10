package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class Main {

	private JFrame frame;
	private JTable productsTable;
	private JTextField textField;
	private Order order;
    private Customer customer;
    private Product products;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 635, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Box horizontalBox = Box.createHorizontalBox();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		
		JButton btnReturnProduct = new JButton("Return Product");
		
		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			        new Cart().setVisible(true);
			}
		});
		
		JButton btnMakeorder = new JButton("Add to Cart");
		final MenuComponent clothes = new Category("Clothes");
		btnMakeorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Category category =new Category("Clothess");
				category.add(new Product(clothes.getChild(productsTable.getSelectedRow()).getName(),clothes.getChild(productsTable.getSelectedRow()).getDescription(),clothes.getChild(productsTable.getSelectedRow()).getPrice(),10));
				String name=textField.getText();
				if(order.CheckProduct(name)==true)
				{
					//customer.MakeOrder(order);
					//products.updateStock();
				}
				else JOptionPane.showMessageDialog(null,"Product Out of Stock");
				
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblSelectedProduct = new JLabel("Selected Product:");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnReturnProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnViewCart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnMakeorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSelectedProduct)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(134)
							.addComponent(btnMakeorder)
							.addGap(18)
							.addComponent(btnViewCart)
							.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
							.addComponent(btnReturnProduct)
							.addGap(90))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelectedProduct)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE))))
		);
		
		JButton btnClothesCategory = new JButton("Clothes");
		btnClothesCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MenuComponent clothes = new Category("Clothes");
				clothes.add(new Product("Shirt", "Style: super comfortable, relaxed fit, lightweight and fashionable.", 200, 20));
				clothes.add(new Product("Shoes", "Upper Material:Genuine Leather", 150, 10));
				clothes.add(new Product("T-Shirt", "Men Crew Neck T-Shirt with Short Sleeves - DARK BLACK", 120, 15));
				clothes.add(new Product("Jacket", "Cotton material, Zipper closure, Long Sleeve and Side pockets", 200, 5));
				
				DefaultTableModel defaultTableModel = new DefaultTableModel();
				
				defaultTableModel.addColumn("Name");
				defaultTableModel.addColumn("Description");
				defaultTableModel.addColumn("Price");
				
				for(MenuComponent menuComponent : clothes.getProducts()) {
					defaultTableModel.addRow(new Object[] {
							menuComponent.getName(), menuComponent.getDescription(),
							menuComponent.getPrice()
					});
				}
				productsTable.setModel(defaultTableModel);
			}
		});
		horizontalBox.add(btnClothesCategory);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		horizontalBox.add(verticalStrut);
		
		productsTable = new JTable();
		productsTable.addMouseListener(new MouseAdapter()
				{
			       public void mouseClicked(MouseEvent arg0)
			       {
			    	   textField.setText(textField.getText()+clothes.getChild(productsTable.getSelectedRow()).getName());
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
